package io.getstream.services;

import io.getstream.models.*;
import io.getstream.models.framework.*;
import io.getstream.services.framework.Client;
import retrofit2.Call;

@lombok.Data
@lombok.NoArgsConstructor
public class Video {
  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class queryCallMembers extends StreamRequest<QueryCallMembersResponse> {
    private QueryCallMembersRequest queryCallMembersRequest;

    @Override
    protected Call<QueryCallMembersResponse> generateCall(Client client) {
      return client.create(VideoService.class).queryCallMembers(this.queryCallMembersRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class queryCallStats extends StreamRequest<QueryCallStatsResponse> {
    private QueryCallStatsRequest queryCallStatsRequest;

    @Override
    protected Call<QueryCallStatsResponse> generateCall(Client client) {
      return client.create(VideoService.class).queryCallStats(this.queryCallStatsRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class getCall extends StreamRequest<GetCallResponse> {
    private String type;
    private String id;
    private Integer membersLimit;
    private Boolean ring;
    private Boolean notify;
    private Boolean video;

    @Override
    protected Call<GetCallResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .getCall(this.type, this.id, this.membersLimit, this.ring, this.notify, this.video);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class updateCall extends StreamRequest<UpdateCallResponse> {
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

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class getOrCreateCall extends StreamRequest<GetOrCreateCallResponse> {
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

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class blockUser extends StreamRequest<BlockUserResponse> {
    private String type;
    private String id;
    private BlockUserRequest blockUserRequest;

    @Override
    protected Call<BlockUserResponse> generateCall(Client client) {
      return client.create(VideoService.class).blockUser(this.type, this.id, this.blockUserRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class deleteCall extends StreamRequest<DeleteCallResponse> {
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

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class sendCallEvent extends StreamRequest<SendCallEventResponse> {
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

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class collectUserFeedback extends StreamRequest<CollectUserFeedbackResponse> {
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

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class goLive extends StreamRequest<GoLiveResponse> {
    private String type;
    private String id;
    private GoLiveRequest goLiveRequest;

    @Override
    protected Call<GoLiveResponse> generateCall(Client client) {
      return client.create(VideoService.class).goLive(this.type, this.id, this.goLiveRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class endCall extends StreamRequest<EndCallResponse> {
    private String type;
    private String id;

    @Override
    protected Call<EndCallResponse> generateCall(Client client) {
      return client.create(VideoService.class).endCall(this.type, this.id);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class updateCallMembers extends StreamRequest<UpdateCallMembersResponse> {
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

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class muteUsers extends StreamRequest<MuteUsersResponse> {
    private String type;
    private String id;
    private MuteUsersRequest muteUsersRequest;

    @Override
    protected Call<MuteUsersResponse> generateCall(Client client) {
      return client.create(VideoService.class).muteUsers(this.type, this.id, this.muteUsersRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class videoPin extends StreamRequest<PinResponse> {
    private String type;
    private String id;
    private PinRequest pinRequest;

    @Override
    protected Call<PinResponse> generateCall(Client client) {
      return client.create(VideoService.class).videoPin(this.type, this.id, this.pinRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class listRecordings extends StreamRequest<ListRecordingsResponse> {
    private String type;
    private String id;

    @Override
    protected Call<ListRecordingsResponse> generateCall(Client client) {
      return client.create(VideoService.class).listRecordings(this.type, this.id);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class startRTMPBroadcasts extends StreamRequest<StartRTMPBroadcastsResponse> {
    private String type;
    private String id;
    private StartRTMPBroadcastsRequest startRTMPBroadcastsRequest;

    @Override
    protected Call<StartRTMPBroadcastsResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .startRTMPBroadcasts(this.type, this.id, this.startRTMPBroadcastsRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class stopAllRTMPBroadcasts extends StreamRequest<StopAllRTMPBroadcastsResponse> {
    private String type;
    private String id;

    @Override
    protected Call<StopAllRTMPBroadcastsResponse> generateCall(Client client) {
      return client.create(VideoService.class).stopAllRTMPBroadcasts(this.type, this.id);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class stopRTMPBroadcast extends StreamRequest<StopRTMPBroadcastsResponse> {
    private String type;
    private String id;
    private String name;
    private StopRTMPBroadcastsRequest stopRTMPBroadcastsRequest;

    @Override
    protected Call<StopRTMPBroadcastsResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .stopRTMPBroadcast(this.type, this.id, this.name, this.stopRTMPBroadcastsRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class startHLSBroadcasting extends StreamRequest<StartHLSBroadcastingResponse> {
    private String type;
    private String id;

    @Override
    protected Call<StartHLSBroadcastingResponse> generateCall(Client client) {
      return client.create(VideoService.class).startHLSBroadcasting(this.type, this.id);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class startClosedCaptions extends StreamRequest<StartClosedCaptionsResponse> {
    private String type;
    private String id;

    @Override
    protected Call<StartClosedCaptionsResponse> generateCall(Client client) {
      return client.create(VideoService.class).startClosedCaptions(this.type, this.id);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class startRecording extends StreamRequest<StartRecordingResponse> {
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

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class startTranscription extends StreamRequest<StartTranscriptionResponse> {
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

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class getCallStats extends StreamRequest<GetCallStatsResponse> {
    private String type;
    private String id;
    private String session;

    @Override
    protected Call<GetCallStatsResponse> generateCall(Client client) {
      return client.create(VideoService.class).getCallStats(this.type, this.id, this.session);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class stopHLSBroadcasting extends StreamRequest<StopHLSBroadcastingResponse> {
    private String type;
    private String id;

    @Override
    protected Call<StopHLSBroadcastingResponse> generateCall(Client client) {
      return client.create(VideoService.class).stopHLSBroadcasting(this.type, this.id);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class stopClosedCaptions extends StreamRequest<StopClosedCaptionsResponse> {
    private String type;
    private String id;

    @Override
    protected Call<StopClosedCaptionsResponse> generateCall(Client client) {
      return client.create(VideoService.class).stopClosedCaptions(this.type, this.id);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class stopLive extends StreamRequest<StopLiveResponse> {
    private String type;
    private String id;
    private StopLiveRequest stopLiveRequest;

    @Override
    protected Call<StopLiveResponse> generateCall(Client client) {
      return client.create(VideoService.class).stopLive(this.type, this.id, this.stopLiveRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class stopRecording extends StreamRequest<StopRecordingResponse> {
    private String type;
    private String id;

    @Override
    protected Call<StopRecordingResponse> generateCall(Client client) {
      return client.create(VideoService.class).stopRecording(this.type, this.id);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class stopTranscription extends StreamRequest<StopTranscriptionResponse> {
    private String type;
    private String id;

    @Override
    protected Call<StopTranscriptionResponse> generateCall(Client client) {
      return client.create(VideoService.class).stopTranscription(this.type, this.id);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class listTranscriptions extends StreamRequest<ListTranscriptionsResponse> {
    private String type;
    private String id;

    @Override
    protected Call<ListTranscriptionsResponse> generateCall(Client client) {
      return client.create(VideoService.class).listTranscriptions(this.type, this.id);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class unblockUser extends StreamRequest<UnblockUserResponse> {
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

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class videoUnpin extends StreamRequest<UnpinResponse> {
    private String type;
    private String id;
    private UnpinRequest unpinRequest;

    @Override
    protected Call<UnpinResponse> generateCall(Client client) {
      return client.create(VideoService.class).videoUnpin(this.type, this.id, this.unpinRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class updateUserPermissions extends StreamRequest<UpdateUserPermissionsResponse> {
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

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class deleteRecording extends StreamRequest<DeleteRecordingResponse> {
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

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class deleteTranscription extends StreamRequest<DeleteTranscriptionResponse> {
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

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class queryCalls extends StreamRequest<QueryCallsResponse> {
    private QueryCallsRequest queryCallsRequest;

    @Override
    protected Call<QueryCallsResponse> generateCall(Client client) {
      return client.create(VideoService.class).queryCalls(this.queryCallsRequest);
    }
  }

  @lombok.NoArgsConstructor
  public static class listCallTypes extends StreamRequest<ListCallTypeResponse> {

    @Override
    protected Call<ListCallTypeResponse> generateCall(Client client) {
      return client.create(VideoService.class).listCallTypes();
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class createCallType extends StreamRequest<CreateCallTypeResponse> {
    private CreateCallTypeRequest createCallTypeRequest;

    @Override
    protected Call<CreateCallTypeResponse> generateCall(Client client) {
      return client.create(VideoService.class).createCallType(this.createCallTypeRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class deleteCallType extends StreamRequest<Response> {
    private String name;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(VideoService.class).deleteCallType(this.name);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class getCallType extends StreamRequest<GetCallTypeResponse> {
    private String name;

    @Override
    protected Call<GetCallTypeResponse> generateCall(Client client) {
      return client.create(VideoService.class).getCallType(this.name);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class updateCallType extends StreamRequest<UpdateCallTypeResponse> {
    private String name;
    private UpdateCallTypeRequest updateCallTypeRequest;

    @Override
    protected Call<UpdateCallTypeResponse> generateCall(Client client) {
      return client
          .create(VideoService.class)
          .updateCallType(this.name, this.updateCallTypeRequest);
    }
  }

  @lombok.NoArgsConstructor
  public static class getEdges extends StreamRequest<GetEdgesResponse> {

    @Override
    protected Call<GetEdgesResponse> generateCall(Client client) {
      return client.create(VideoService.class).getEdges();
    }
  }
}
