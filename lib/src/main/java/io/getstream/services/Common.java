package io.getstream.services;

import io.getstream.models.*;
import io.getstream.models.framework.*;
import io.getstream.services.framework.Client;
import lombok.*;
import retrofit2.Call;

@Data
@NoArgsConstructor
public class Common {
  @NoArgsConstructor
  public static class GetApp extends StreamRequest<GetApplicationResponse> {

    @Override
    protected Call<GetApplicationResponse> generateCall(Client client) {
      return client.create(CommonService.class).getApp();
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class UpdateApp extends StreamRequest<Response> {
    private UpdateAppRequest updateAppRequest;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).updateApp(this.updateAppRequest);
    }
  }

  @NoArgsConstructor
  public static class ListBlockLists extends StreamRequest<ListBlockListResponse> {

    @Override
    protected Call<ListBlockListResponse> generateCall(Client client) {
      return client.create(CommonService.class).listBlockLists();
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class CreateBlockList extends StreamRequest<Response> {
    private CreateBlockListRequest createBlockListRequest;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).createBlockList(this.createBlockListRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class DeleteBlockList extends StreamRequest<Response> {
    private String name;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).deleteBlockList(this.name);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class GetBlockList extends StreamRequest<GetBlockListResponse> {
    private String name;

    @Override
    protected Call<GetBlockListResponse> generateCall(Client client) {
      return client.create(CommonService.class).getBlockList(this.name);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class UpdateBlockList extends StreamRequest<Response> {
    private String name;
    private UpdateBlockListRequest updateBlockListRequest;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client
          .create(CommonService.class)
          .updateBlockList(this.name, this.updateBlockListRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class CheckPush extends StreamRequest<CheckPushResponse> {
    private CheckPushRequest checkPushRequest;

    @Override
    protected Call<CheckPushResponse> generateCall(Client client) {
      return client.create(CommonService.class).checkPush(this.checkPushRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class CheckSNS extends StreamRequest<CheckSNSResponse> {
    private CheckSNSRequest checkSNSRequest;

    @Override
    protected Call<CheckSNSResponse> generateCall(Client client) {
      return client.create(CommonService.class).checkSNS(this.checkSNSRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class CheckSQS extends StreamRequest<CheckSQSResponse> {
    private CheckSQSRequest checkSQSRequest;

    @Override
    protected Call<CheckSQSResponse> generateCall(Client client) {
      return client.create(CommonService.class).checkSQS(this.checkSQSRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class DeleteDevice extends StreamRequest<Response> {
    private String id;
    private String userId;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).deleteDevice(this.id, this.userId);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class ListDevices extends StreamRequest<ListDevicesResponse> {
    private String userId;

    @Override
    protected Call<ListDevicesResponse> generateCall(Client client) {
      return client.create(CommonService.class).listDevices(this.userId);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class CreateDevice extends StreamRequest<Response> {
    private CreateDeviceRequest createDeviceRequest;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).createDevice(this.createDeviceRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class ExportUsers extends StreamRequest<ExportUsersResponse> {
    private ExportUsersRequest exportUsersRequest;

    @Override
    protected Call<ExportUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).exportUsers(this.exportUsersRequest);
    }
  }

  @NoArgsConstructor
  public static class ListExternalStorage extends StreamRequest<ListExternalStorageResponse> {

    @Override
    protected Call<ListExternalStorageResponse> generateCall(Client client) {
      return client.create(CommonService.class).listExternalStorage();
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class CreateExternalStorage extends StreamRequest<CreateExternalStorageResponse> {
    private CreateExternalStorageRequest createExternalStorageRequest;

    @Override
    protected Call<CreateExternalStorageResponse> generateCall(Client client) {
      return client
          .create(CommonService.class)
          .createExternalStorage(this.createExternalStorageRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class DeleteExternalStorage extends StreamRequest<DeleteExternalStorageResponse> {
    private String name;

    @Override
    protected Call<DeleteExternalStorageResponse> generateCall(Client client) {
      return client.create(CommonService.class).deleteExternalStorage(this.name);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class UpdateExternalStorage extends StreamRequest<UpdateExternalStorageResponse> {
    private String name;
    private UpdateExternalStorageRequest updateExternalStorageRequest;

    @Override
    protected Call<UpdateExternalStorageResponse> generateCall(Client client) {
      return client
          .create(CommonService.class)
          .updateExternalStorage(this.name, this.updateExternalStorageRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class CheckExternalStorage extends StreamRequest<CheckExternalStorageResponse> {
    private String name;

    @Override
    protected Call<CheckExternalStorageResponse> generateCall(Client client) {
      return client.create(CommonService.class).checkExternalStorage(this.name);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class CreateGuest extends StreamRequest<CreateGuestResponse> {
    private CreateGuestRequest createGuestRequest;

    @Override
    protected Call<CreateGuestResponse> generateCall(Client client) {
      return client.create(CommonService.class).createGuest(this.createGuestRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class CreateImportURL extends StreamRequest<CreateImportURLResponse> {
    private CreateImportURLRequest createImportURLRequest;

    @Override
    protected Call<CreateImportURLResponse> generateCall(Client client) {
      return client.create(CommonService.class).createImportURL(this.createImportURLRequest);
    }
  }

  @NoArgsConstructor
  public static class ListImports extends StreamRequest<ListImportsResponse> {

    @Override
    protected Call<ListImportsResponse> generateCall(Client client) {
      return client.create(CommonService.class).listImports();
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class CreateImport extends StreamRequest<CreateImportResponse> {
    private CreateImportRequest createImportRequest;

    @Override
    protected Call<CreateImportResponse> generateCall(Client client) {
      return client.create(CommonService.class).createImport(this.createImportRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class GetImport extends StreamRequest<GetImportResponse> {
    private String id;

    @Override
    protected Call<GetImportResponse> generateCall(Client client) {
      return client.create(CommonService.class).getImport(this.id);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class Unban extends StreamRequest<Response> {
    private String targetUserId;
    private String channelCid;
    private String createdBy;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client
          .create(CommonService.class)
          .unban(this.targetUserId, this.channelCid, this.createdBy);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class Ban extends StreamRequest<Response> {
    private BanRequest banRequest;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).ban(this.banRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class Flag extends StreamRequest<FlagResponse> {
    private FlagRequest flagRequest;

    @Override
    protected Call<FlagResponse> generateCall(Client client) {
      return client.create(CommonService.class).flag(this.flagRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class MuteUser extends StreamRequest<MuteUserResponse> {
    private MuteUserRequest muteUserRequest;

    @Override
    protected Call<MuteUserResponse> generateCall(Client client) {
      return client.create(CommonService.class).muteUser(this.muteUserRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class UnmuteUser extends StreamRequest<UnmuteResponse> {
    private UnmuteUserRequest unmuteUserRequest;

    @Override
    protected Call<UnmuteResponse> generateCall(Client client) {
      return client.create(CommonService.class).unmuteUser(this.unmuteUserRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class GetOG extends StreamRequest<GetOGResponse> {
    private String url;

    @Override
    protected Call<GetOGResponse> generateCall(Client client) {
      return client.create(CommonService.class).getOG(this.url);
    }
  }

  @NoArgsConstructor
  public static class ListPermissions extends StreamRequest<ListPermissionsResponse> {

    @Override
    protected Call<ListPermissionsResponse> generateCall(Client client) {
      return client.create(CommonService.class).listPermissions();
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class GetPermission extends StreamRequest<GetCustomPermissionResponse> {
    private String id;

    @Override
    protected Call<GetCustomPermissionResponse> generateCall(Client client) {
      return client.create(CommonService.class).getPermission(this.id);
    }
  }

  @NoArgsConstructor
  public static class ListPushProviders extends StreamRequest<ListPushProvidersResponse> {

    @Override
    protected Call<ListPushProvidersResponse> generateCall(Client client) {
      return client.create(CommonService.class).listPushProviders();
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class UpsertPushProvider extends StreamRequest<UpsertPushProviderResponse> {
    private UpsertPushProviderRequest upsertPushProviderRequest;

    @Override
    protected Call<UpsertPushProviderResponse> generateCall(Client client) {
      return client.create(CommonService.class).upsertPushProvider(this.upsertPushProviderRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class DeletePushProvider extends StreamRequest<Response> {
    private String type;
    private String name;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).deletePushProvider(this.type, this.name);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class GetRateLimits extends StreamRequest<GetRateLimitsResponse> {
    private Boolean serverSide;
    private Boolean android;
    private Boolean ios;
    private Boolean web;
    private String endpoints;

    @Override
    protected Call<GetRateLimitsResponse> generateCall(Client client) {
      return client
          .create(CommonService.class)
          .getRateLimits(this.serverSide, this.android, this.ios, this.web, this.endpoints);
    }
  }

  @NoArgsConstructor
  public static class ListRoles extends StreamRequest<ListRolesResponse> {

    @Override
    protected Call<ListRolesResponse> generateCall(Client client) {
      return client.create(CommonService.class).listRoles();
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class CreateRole extends StreamRequest<CreateRoleResponse> {
    private CreateRoleRequest createRoleRequest;

    @Override
    protected Call<CreateRoleResponse> generateCall(Client client) {
      return client.create(CommonService.class).createRole(this.createRoleRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class DeleteRole extends StreamRequest<Response> {
    private String name;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).deleteRole(this.name);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class GetTask extends StreamRequest<GetTaskResponse> {
    private String id;

    @Override
    protected Call<GetTaskResponse> generateCall(Client client) {
      return client.create(CommonService.class).getTask(this.id);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class QueryUsers extends StreamRequest<QueryUsersResponse> {
    private QueryUsersPayload payload;

    @Override
    protected Call<QueryUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).queryUsers(this.payload);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class UpdateUsersPartial extends StreamRequest<UpdateUsersResponse> {
    private UpdateUsersPartialRequest updateUsersPartialRequest;

    @Override
    protected Call<UpdateUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).updateUsersPartial(this.updateUsersPartialRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class UpdateUsers extends StreamRequest<UpdateUsersResponse> {
    private UpdateUsersRequest updateUsersRequest;

    @Override
    protected Call<UpdateUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).updateUsers(this.updateUsersRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class GetBlockedUsers extends StreamRequest<GetBlockedUsersResponse> {
    private String userId;

    @Override
    protected Call<GetBlockedUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).getBlockedUsers(this.userId);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class BlockUsers extends StreamRequest<BlockUsersResponse> {
    private BlockUsersRequest blockUsersRequest;

    @Override
    protected Call<BlockUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).blockUsers(this.blockUsersRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class DeactivateUsers extends StreamRequest<DeactivateUsersResponse> {
    private DeactivateUsersRequest deactivateUsersRequest;

    @Override
    protected Call<DeactivateUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).deactivateUsers(this.deactivateUsersRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class DeleteUsers extends StreamRequest<DeleteUsersResponse> {
    private DeleteUsersRequest deleteUsersRequest;

    @Override
    protected Call<DeleteUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).deleteUsers(this.deleteUsersRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class ReactivateUsers extends StreamRequest<ReactivateUsersResponse> {
    private ReactivateUsersRequest reactivateUsersRequest;

    @Override
    protected Call<ReactivateUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).reactivateUsers(this.reactivateUsersRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class RestoreUsers extends StreamRequest<Response> {
    private RestoreUsersRequest restoreUsersRequest;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).restoreUsers(this.restoreUsersRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class UnblockUsers extends StreamRequest<UnblockUsersResponse> {
    private UnblockUsersRequest unblockUsersRequest;

    @Override
    protected Call<UnblockUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).unblockUsers(this.unblockUsersRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class DeactivateUser extends StreamRequest<DeactivateUserResponse> {
    private String userId;
    private DeactivateUserRequest deactivateUserRequest;

    @Override
    protected Call<DeactivateUserResponse> generateCall(Client client) {
      return client
          .create(CommonService.class)
          .deactivateUser(this.userId, this.deactivateUserRequest);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class ExportUser extends StreamRequest<ExportUserResponse> {
    private String userId;

    @Override
    protected Call<ExportUserResponse> generateCall(Client client) {
      return client.create(CommonService.class).exportUser(this.userId);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  public static class ReactivateUser extends StreamRequest<ReactivateUserResponse> {
    private String userId;
    private ReactivateUserRequest reactivateUserRequest;

    @Override
    protected Call<ReactivateUserResponse> generateCall(Client client) {
      return client
          .create(CommonService.class)
          .reactivateUser(this.userId, this.reactivateUserRequest);
    }
  }
}
