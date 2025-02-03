package io.getstream;

import io.getstream.models.ChannelGetOrCreateRequest;
import io.getstream.models.ChannelInput;
import io.getstream.models.ChannelMember;
import io.getstream.models.UpdateChannelRequest;
import io.getstream.services.Channel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChannelsTest extends BasicTest {
  private Channel channels = new Channel("messaging", "java_test", chat);

  @DisplayName("Can create channel")
  @Test
  void whenCreatingChannel_thenNoException() {
    Assertions.assertDoesNotThrow(
        () ->
            this.channels.getOrCreate(
                ChannelGetOrCreateRequest.builder()
                    .data(ChannelInput.builder().createdByID(testUser.getId()).build())
                    .build()));
  }

  @DisplayName("can update channel")
  @Test
  void whenUpdatingChannel_thenNoException() {
    Assertions.assertDoesNotThrow(
        () ->
            this.channels.update(
                UpdateChannelRequest.builder()
                    .data(
                        ChannelInput.builder()
                            .members(
                                testUsers.subList(0, 1).stream()
                                    .map(
                                        fullUserResponse -> {
                                          return ChannelMember.builder()
                                              .userID(fullUserResponse.getId())
                                              .build();
                                        })
                                    .toList())
                            .build())
                    .build()));
  }
}
