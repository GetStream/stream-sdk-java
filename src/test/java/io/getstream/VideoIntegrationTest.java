package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import io.getstream.models.*;
import java.util.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;

/**
 * Video integration tests for stream-sdk-java, ported from the getstream-go reference
 * implementation.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VideoIntegrationTest extends BasicTest {

  private static String callTypeName;

  @BeforeAll
  static void setupCallType() throws Exception {
    // Create a unique call type for all tests in this class
    callTypeName = "calltype-" + RandomStringUtils.randomAlphabetic(8).toLowerCase();

    Map<String, List<String>> grants =
        Map.of(
            "admin", List.of("send-audio", "send-video", "mute-users"),
            "user", List.of("send-audio", "send-video"));

    video
        .createCallType(
            CreateCallTypeRequest.builder()
                .name(callTypeName)
                .grants(grants)
                .settings(
                    CallSettingsRequest.builder()
                        .audio(
                            AudioSettingsRequest.builder()
                                .defaultDevice("speaker")
                                .micDefaultOn(true)
                                .build())
                        .screensharing(
                            ScreensharingSettingsRequest.builder()
                                .accessRequestEnabled(false)
                                .enabled(true)
                                .build())
                        .build())
                .build())
        .execute();

    // Wait for eventual consistency after creating call type
    Thread.sleep(6000);
  }

  @AfterAll
  static void tearDownCallType() {
    if (callTypeName != null) {
      try {
        video.deleteCallType(callTypeName).execute();
      } catch (Exception ignored) {
      }
    }
  }

  @Test
  @Order(1)
  void testCRUDCallTypeOperations() throws Exception {
    // --- Update call type settings ---
    Map<String, List<String>> updatedGrants = Map.of("host", List.of("join-backstage"));

    var updateResp =
        video
            .updateCallType(
                callTypeName,
                UpdateCallTypeRequest.builder()
                    .settings(
                        CallSettingsRequest.builder()
                            .audio(
                                AudioSettingsRequest.builder()
                                    .defaultDevice("earpiece")
                                    .micDefaultOn(false)
                                    .build())
                            .recording(RecordSettingsRequest.builder().mode("disabled").build())
                            .backstage(BackstageSettingsRequest.builder().enabled(true).build())
                            .build())
                    .grants(updatedGrants)
                    .build())
            .execute();

    assertNotNull(updateResp.getData());
    assertFalse(updateResp.getData().getSettings().getAudio().getMicDefaultOn());
    assertEquals("earpiece", updateResp.getData().getSettings().getAudio().getDefaultDevice());
    assertEquals("disabled", updateResp.getData().getSettings().getRecording().getMode());
    assertTrue(updateResp.getData().getSettings().getBackstage().getEnabled());
    assertEquals(
        List.of("join-backstage"), updateResp.getData().getGrants().get("host"));

    // --- Update layout options ---
    Map<String, Object> layoutOptions = new HashMap<>();
    layoutOptions.put(
        "logo.image_url",
        "https://theme.zdassets.com/theme_assets/9442057/efc3820e436f9150bc8cf34267fff4df052a1f9c.png");
    layoutOptions.put("logo.horizontal_position", "center");
    layoutOptions.put("title.text", "Building Stream Video Q&A");
    layoutOptions.put("title.horizontal_position", "center");
    layoutOptions.put("title.color", "black");
    layoutOptions.put("participant_label.border_radius", "0px");
    layoutOptions.put("participant.border_radius", "0px");
    layoutOptions.put("layout.spotlight.participants_bar_position", "top");
    layoutOptions.put("layout.background_color", "#f2f2f2");
    layoutOptions.put("participant.placeholder_background_color", "#1f1f1f");
    layoutOptions.put("layout.single-participant.padding_inline", "20%");
    layoutOptions.put("participant_label.background_color", "transparent");

    var layoutResp =
        video
            .updateCallType(
                callTypeName,
                UpdateCallTypeRequest.builder()
                    .settings(
                        CallSettingsRequest.builder()
                            .recording(
                                RecordSettingsRequest.builder()
                                    .mode("available")
                                    .audioOnly(false)
                                    .quality("1080p")
                                    .layout(
                                        LayoutSettingsRequest.builder()
                                            .name("spotlight")
                                            .options(layoutOptions)
                                            .build())
                                    .build())
                            .build())
                    .build())
            .execute();
    assertNotNull(layoutResp.getData());

    // --- Update custom recording CSS ---
    var cssResp =
        video
            .updateCallType(
                callTypeName,
                UpdateCallTypeRequest.builder()
                    .settings(
                        CallSettingsRequest.builder()
                            .recording(
                                RecordSettingsRequest.builder()
                                    .mode("available")
                                    .audioOnly(false)
                                    .quality("1080p")
                                    .layout(
                                        LayoutSettingsRequest.builder()
                                            .name("spotlight")
                                            .externalCssUrl("https://path/to/custom.css")
                                            .build())
                                    .build())
                            .build())
                    .build())
            .execute();
    assertNotNull(cssResp.getData());

    // --- Update custom recording website ---
    var webResp =
        video
            .updateCallType(
                callTypeName,
                UpdateCallTypeRequest.builder()
                    .settings(
                        CallSettingsRequest.builder()
                            .recording(
                                RecordSettingsRequest.builder()
                                    .mode("available")
                                    .audioOnly(false)
                                    .quality("1080p")
                                    .layout(
                                        LayoutSettingsRequest.builder()
                                            .name("custom")
                                            .externalAppUrl("https://path/to/layout/app")
                                            .build())
                                    .build())
                            .build())
                    .build())
            .execute();
    assertNotNull(webResp.getData());

    // --- Read call type and verify name ---
    var readResp = video.getCallType(callTypeName).execute();
    assertNotNull(readResp.getData());
    assertEquals(callTypeName, readResp.getData().getName());
  }
}
