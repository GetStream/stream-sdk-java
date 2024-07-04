package io.getstream.services;

import io.getstream.models.*;
import io.getstream.models.framework.*;
import io.getstream.services.framework.Client;
import lombok.*;
import retrofit2.Call;

@Data
@NoArgsConstructor
public class Video {
  @NoArgsConstructor
  @AllArgsConstructor
  public static class QueryCallMembers extends StreamRequest<QueryCallMembersResponse> {
    private QueryCallMembersRequest queryCallMembersRequest;

    @Override
    protected Call<QueryCallMembersResponse> generateCall(Client client) {
      return client.create(VideoService.class).queryCallMembers(this.queryCallMembersRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class QueryCallStats extends StreamRequest<QueryCallStatsResponse> {
    private QueryCallStatsRequest queryCallStatsRequest;

    @Override
    protected Call<QueryCallStatsResponse> generateCall(Client client) {
      return client.create(VideoService.class).queryCallStats(this.queryCallStatsRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class GetCall extends StreamRequest<GetCallResponse> {
    private String type;
    private String id;
    private Integer membersLimit;
    private Boolean ring;
    private Boolean notify;

    @Override
    protected Call<GetCallResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .getCall(this.type, this.id, this.membersLimit, this.ring, this.notify);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class UpdateCall extends StreamRequest<UpdateCallResponse> {
    private String type;
    private String id;
    private UpdateCallRequest updateCallRequest;

    @Override
    protected Call<UpdateCallResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .updateCall(this.type, this.id, this.updateCallRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class GetOrCreateCall extends StreamRequest<GetOrCreateCallResponse> {
    private String type;
    private String id;
    private GetOrCreateCallRequest getOrCreateCallRequest;

    @Override
    protected Call<GetOrCreateCallResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .getOrCreateCall(this.type, this.id, this.getOrCreateCallRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class BlockUser extends StreamRequest<BlockUserResponse> {
    private String type;
    private String id;
    private BlockUserRequest blockUserRequest;

    @Override
    protected Call<BlockUserResponse> generateCall(Client client) {
      return client.create(VideoService.class).blockUser(this.type, this.id, this.blockUserRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class DeleteCall extends StreamRequest<DeleteCallResponse> {
    private String type;
    private String id;
    private DeleteCallRequest deleteCallRequest;

    @Override
    protected Call<DeleteCallResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .deleteCall(this.type, this.id, this.deleteCallRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class SendCallEvent extends StreamRequest<SendCallEventResponse> {
    private String type;
    private String id;
    private SendCallEventRequest sendCallEventRequest;

    @Override
    protected Call<SendCallEventResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .sendCallEvent(this.type, this.id, this.sendCallEventRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class CollectUserFeedback extends StreamRequest<CollectUserFeedbackResponse> {
    private String type;
    private String id;
    private String session;
    private CollectUserFeedbackRequest collectUserFeedbackRequest;

    @Override
    protected Call<CollectUserFeedbackResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .collectUserFeedback(this.type, this.id, this.session, this.collectUserFeedbackRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class GoLive extends StreamRequest<GoLiveResponse> {
    private String type;
    private String id;
    private GoLiveRequest goLiveRequest;

    @Override
    protected Call<GoLiveResponse> generateCall(Client client) {
      return client.create(VideoService.class).goLive(this.type, this.id, this.goLiveRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class EndCall extends StreamRequest<EndCallResponse> {
    private String type;
    private String id;

    @Override
    protected Call<EndCallResponse> generateCall(Client client) {
      return client.create(VideoService.class).endCall(this.type, this.id);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class UpdateCallMembers extends StreamRequest<UpdateCallMembersResponse> {
    private String type;
    private String id;
    private UpdateCallMembersRequest updateCallMembersRequest;

    @Override
    protected Call<UpdateCallMembersResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .updateCallMembers(this.type, this.id, this.updateCallMembersRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class MuteUsers extends StreamRequest<MuteUsersResponse> {
    private String type;
    private String id;
    private MuteUsersRequest muteUsersRequest;

    @Override
    protected Call<MuteUsersResponse> generateCall(Client client) {
      return client.create(VideoService.class).muteUsers(this.type, this.id, this.muteUsersRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class VideoPin extends StreamRequest<PinResponse> {
    private String type;
    private String id;
    private PinRequest pinRequest;

    @Override
    protected Call<PinResponse> generateCall(Client client) {
      return client.create(VideoService.class).videoPin(this.type, this.id, this.pinRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class ListRecordings extends StreamRequest<ListRecordingsResponse> {
    private String type;
    private String id;

    @Override
    protected Call<ListRecordingsResponse> generateCall(Client client) {
      return client.create(VideoService.class).listRecordings(this.type, this.id);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class StartHLSBroadcasting extends StreamRequest<StartHLSBroadcastingResponse> {
    private String type;
    private String id;

    @Override
    protected Call<StartHLSBroadcastingResponse> generateCall(Client client) {
      return client.create(VideoService.class).startHLSBroadcasting(this.type, this.id);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class StartRecording extends StreamRequest<StartRecordingResponse> {
    private String type;
    private String id;
    private StartRecordingRequest startRecordingRequest;

    @Override
    protected Call<StartRecordingResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .startRecording(this.type, this.id, this.startRecordingRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class StartTranscription extends StreamRequest<StartTranscriptionResponse> {
    private String type;
    private String id;
    private StartTranscriptionRequest startTranscriptionRequest;

    @Override
    protected Call<StartTranscriptionResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .startTranscription(this.type, this.id, this.startTranscriptionRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class GetCallStats extends StreamRequest<GetCallStatsResponse> {
    private String type;
    private String id;
    private String session;

    @Override
    protected Call<GetCallStatsResponse> generateCall(Client client) {
      return client.create(VideoService.class).getCallStats(this.type, this.id, this.session);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class StopHLSBroadcasting extends StreamRequest<StopHLSBroadcastingResponse> {
    private String type;
    private String id;

    @Override
    protected Call<StopHLSBroadcastingResponse> generateCall(Client client) {
      return client.create(VideoService.class).stopHLSBroadcasting(this.type, this.id);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class StopLive extends StreamRequest<StopLiveResponse> {
    private String type;
    private String id;

    @Override
    protected Call<StopLiveResponse> generateCall(Client client) {
      return client.create(VideoService.class).stopLive(this.type, this.id);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class StopRecording extends StreamRequest<StopRecordingResponse> {
    private String type;
    private String id;

    @Override
    protected Call<StopRecordingResponse> generateCall(Client client) {
      return client.create(VideoService.class).stopRecording(this.type, this.id);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class StopTranscription extends StreamRequest<StopTranscriptionResponse> {
    private String type;
    private String id;

    @Override
    protected Call<StopTranscriptionResponse> generateCall(Client client) {
      return client.create(VideoService.class).stopTranscription(this.type, this.id);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class ListTranscriptions extends StreamRequest<ListTranscriptionsResponse> {
    private String type;
    private String id;

    @Override
    protected Call<ListTranscriptionsResponse> generateCall(Client client) {
      return client.create(VideoService.class).listTranscriptions(this.type, this.id);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class UnblockUser extends StreamRequest<UnblockUserResponse> {
    private String type;
    private String id;
    private UnblockUserRequest unblockUserRequest;

    @Override
    protected Call<UnblockUserResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .unblockUser(this.type, this.id, this.unblockUserRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class VideoUnpin extends StreamRequest<UnpinResponse> {
    private String type;
    private String id;
    private UnpinRequest unpinRequest;

    @Override
    protected Call<UnpinResponse> generateCall(Client client) {
      return client.create(VideoService.class).videoUnpin(this.type, this.id, this.unpinRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class UpdateUserPermissions extends StreamRequest<UpdateUserPermissionsResponse> {
    private String type;
    private String id;
    private UpdateUserPermissionsRequest updateUserPermissionsRequest;

    @Override
    protected Call<UpdateUserPermissionsResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .updateUserPermissions(this.type, this.id, this.updateUserPermissionsRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class DeleteRecording extends StreamRequest<DeleteRecordingResponse> {
    private String type;
    private String id;
    private String session;
    private String filename;

    @Override
    protected Call<DeleteRecordingResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .deleteRecording(this.type, this.id, this.session, this.filename);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class DeleteTranscription extends StreamRequest<DeleteTranscriptionResponse> {
    private String type;
    private String id;
    private String session;
    private String filename;

    @Override
    protected Call<DeleteTranscriptionResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .deleteTranscription(this.type, this.id, this.session, this.filename);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class QueryCalls extends StreamRequest<QueryCallsResponse> {
    private QueryCallsRequest queryCallsRequest;

    @Override
    protected Call<QueryCallsResponse> generateCall(Client client) {
      return client.create(VideoService.class).queryCalls(this.queryCallsRequest);
    }
  }

  @NoArgsConstructor
  public static class ListCallTypes extends StreamRequest<ListCallTypeResponse> {

    @Override
    protected Call<ListCallTypeResponse> generateCall(Client client) {
      return client.create(VideoService.class).listCallTypes();
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class CreateCallType extends StreamRequest<CreateCallTypeResponse> {
    private CreateCallTypeRequest createCallTypeRequest;

    @Override
    protected Call<CreateCallTypeResponse> generateCall(Client client) {
      return client.create(VideoService.class).createCallType(this.createCallTypeRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class DeleteCallType extends StreamRequest<Response> {
    private String name;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(VideoService.class).deleteCallType(this.name);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class GetCallType extends StreamRequest<GetCallTypeResponse> {
    private String name;

    @Override
    protected Call<GetCallTypeResponse> generateCall(Client client) {
      return client.create(VideoService.class).getCallType(this.name);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class UpdateCallType extends StreamRequest<UpdateCallTypeResponse> {
    private String name;
    private UpdateCallTypeRequest updateCallTypeRequest;

    @Override
    protected Call<UpdateCallTypeResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .updateCallType(this.name, this.updateCallTypeRequest);
    }
  }

  @NoArgsConstructor
  public static class GetEdges extends StreamRequest<GetEdgesResponse> {

    @Override
    protected Call<GetEdgesResponse> generateCall(Client client) {
      return client.create(VideoService.class).getEdges();
    }
  }
}
