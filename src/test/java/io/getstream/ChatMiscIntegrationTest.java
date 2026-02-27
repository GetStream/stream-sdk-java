package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import io.getstream.models.*;
import java.util.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChatMiscIntegrationTest extends ChatTestBase {

  private final List<String> createdUserIds = new ArrayList<>();

  @AfterAll
  void cleanup() {
    if (!createdUserIds.isEmpty()) {
      deleteUsersWithRetry(createdUserIds);
    }
  }

  @Test
  @Order(1)
  void testCreateListDeleteDevice() throws Exception {
    List<String> userIds = createTestUsers(1);
    createdUserIds.addAll(userIds);
    String userId = userIds.get(0);

    String deviceId = "integration-test-device-" + randomString(12);

    try {
      // Create device
      client
          .createDevice(
              CreateDeviceRequest.builder()
                  .id(deviceId)
                  .pushProvider("firebase")
                  .userID(userId)
                  .build())
          .execute();
    } catch (Exception e) {
      String msg = e.getMessage();
      if (msg != null
          && (msg.contains("no push providers configured")
              || msg.contains("push provider")
              || msg.contains("push notifications"))) {
        Assumptions.assumeTrue(false, "Push providers not configured for this app");
      }
      throw e;
    }

    // List devices and verify found
    var listResp =
        client
            .listDevices(ListDevicesRequest.builder().UserID(userId).build())
            .execute();

    assertNotNull(listResp.getData().getDevices());
    boolean found = false;
    for (DeviceResponse d : listResp.getData().getDevices()) {
      if (deviceId.equals(d.getId())) {
        found = true;
        assertEquals("firebase", d.getPushProvider());
        assertEquals(userId, d.getUserID());
      }
    }
    assertTrue(found, "Created device should appear in list");

    // Delete device
    client
        .deleteDevice(DeleteDeviceRequest.builder().ID(deviceId).UserID(userId).build())
        .execute();

    // Verify deleted
    var listAfterDelete =
        client
            .listDevices(ListDevicesRequest.builder().UserID(userId).build())
            .execute();

    if (listAfterDelete.getData().getDevices() != null) {
      for (DeviceResponse d : listAfterDelete.getData().getDevices()) {
        assertNotEquals(deviceId, d.getId(), "Device should be deleted");
      }
    }
  }
}
