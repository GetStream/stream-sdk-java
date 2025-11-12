package io.getstream;

import io.getstream.models.*;
import io.getstream.services.Channel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChannelsTest extends BasicTest {
  private Channel channel = chat.channel("messaging", "java_test");

  @DisplayName("Can create channel")
  @Test
  void whenCreatingChannel_thenNoException() {
    Assertions.assertDoesNotThrow(
        () ->
            this.channel.getOrCreate(
                GetOrCreateChannelRequest.builder()
                    .data(ChannelInput.builder().createdByID(testUser.getId()).build())
                    .build()));
  }

  @DisplayName("can update channel")
  @Test
  void whenUpdatingChannel_thenNoException() {
    Assertions.assertDoesNotThrow(
        () ->
            this.channel.update(
                UpdateChannelRequest.builder()
                    .data(
                        ChannelInput.builder()
                            .members(
                                testUsers.subList(0, 1).stream()
                                    .map(
                                        fullUserResponse -> {
                                          return ChannelMemberRequest.builder()
                                              .userID(fullUserResponse.getId())
                                              .build();
                                        })
                                    .toList())
                            .build())
                    .build()));
  }
}
