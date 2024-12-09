package io.getstream.services;

import io.getstream.models.*;
import io.getstream.models.framework.*;
import io.getstream.services.framework.Client;
import retrofit2.Call;

@lombok.Data
@lombok.NoArgsConstructor
public class Common {
  @lombok.NoArgsConstructor
  public static class getApp extends StreamRequest<GetApplicationResponse> {

    @Override
    protected Call<GetApplicationResponse> generateCall(Client client) {
      return client.create(CommonService.class).getApp();
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class updateApp extends StreamRequest<Response> {
    private UpdateAppRequest updateAppRequest;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).updateApp(this.updateAppRequest);
    }
  }

  @lombok.NoArgsConstructor
  public static class listBlockLists extends StreamRequest<ListBlockListResponse> {

    @Override
    protected Call<ListBlockListResponse> generateCall(Client client) {
      return client.create(CommonService.class).listBlockLists();
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class createBlockList extends StreamRequest<Response> {
    private CreateBlockListRequest createBlockListRequest;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).createBlockList(this.createBlockListRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class deleteBlockList extends StreamRequest<Response> {
    private String name;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).deleteBlockList(this.name);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class getBlockList extends StreamRequest<GetBlockListResponse> {
    private String name;

    @Override
    protected Call<GetBlockListResponse> generateCall(Client client) {
      return client.create(CommonService.class).getBlockList(this.name);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class updateBlockList extends StreamRequest<Response> {
    private String name;
    private UpdateBlockListRequest updateBlockListRequest;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client
          .create(CommonService.class)
          .updateBlockList(this.name, this.updateBlockListRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class checkPush extends StreamRequest<CheckPushResponse> {
    private CheckPushRequest checkPushRequest;

    @Override
    protected Call<CheckPushResponse> generateCall(Client client) {
      return client.create(CommonService.class).checkPush(this.checkPushRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class checkSNS extends StreamRequest<CheckSNSResponse> {
    private CheckSNSRequest checkSNSRequest;

    @Override
    protected Call<CheckSNSResponse> generateCall(Client client) {
      return client.create(CommonService.class).checkSNS(this.checkSNSRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class checkSQS extends StreamRequest<CheckSQSResponse> {
    private CheckSQSRequest checkSQSRequest;

    @Override
    protected Call<CheckSQSResponse> generateCall(Client client) {
      return client.create(CommonService.class).checkSQS(this.checkSQSRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class deleteDevice extends StreamRequest<Response> {
    private String id;
    private String userID;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).deleteDevice(this.id, this.userID);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class listDevices extends StreamRequest<ListDevicesResponse> {
    private String userID;

    @Override
    protected Call<ListDevicesResponse> generateCall(Client client) {
      return client.create(CommonService.class).listDevices(this.userID);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class createDevice extends StreamRequest<Response> {
    private CreateDeviceRequest createDeviceRequest;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).createDevice(this.createDeviceRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class exportUsers extends StreamRequest<ExportUsersResponse> {
    private ExportUsersRequest exportUsersRequest;

    @Override
    protected Call<ExportUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).exportUsers(this.exportUsersRequest);
    }
  }

  @lombok.NoArgsConstructor
  public static class listExternalStorage extends StreamRequest<ListExternalStorageResponse> {

    @Override
    protected Call<ListExternalStorageResponse> generateCall(Client client) {
      return client.create(CommonService.class).listExternalStorage();
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class createExternalStorage extends StreamRequest<CreateExternalStorageResponse> {
    private CreateExternalStorageRequest createExternalStorageRequest;

    @Override
    protected Call<CreateExternalStorageResponse> generateCall(Client client) {
      return client
          .create(CommonService.class)
          .createExternalStorage(this.createExternalStorageRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class deleteExternalStorage extends StreamRequest<DeleteExternalStorageResponse> {
    private String name;

    @Override
    protected Call<DeleteExternalStorageResponse> generateCall(Client client) {
      return client.create(CommonService.class).deleteExternalStorage(this.name);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class updateExternalStorage extends StreamRequest<UpdateExternalStorageResponse> {
    private String name;
    private UpdateExternalStorageRequest updateExternalStorageRequest;

    @Override
    protected Call<UpdateExternalStorageResponse> generateCall(Client client) {
      return client
          .create(CommonService.class)
          .updateExternalStorage(this.name, this.updateExternalStorageRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class checkExternalStorage extends StreamRequest<CheckExternalStorageResponse> {
    private String name;

    @Override
    protected Call<CheckExternalStorageResponse> generateCall(Client client) {
      return client.create(CommonService.class).checkExternalStorage(this.name);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class createGuest extends StreamRequest<CreateGuestResponse> {
    private CreateGuestRequest createGuestRequest;

    @Override
    protected Call<CreateGuestResponse> generateCall(Client client) {
      return client.create(CommonService.class).createGuest(this.createGuestRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class createImportURL extends StreamRequest<CreateImportURLResponse> {
    private CreateImportURLRequest createImportURLRequest;

    @Override
    protected Call<CreateImportURLResponse> generateCall(Client client) {
      return client.create(CommonService.class).createImportURL(this.createImportURLRequest);
    }
  }

  @lombok.NoArgsConstructor
  public static class listImports extends StreamRequest<ListImportsResponse> {

    @Override
    protected Call<ListImportsResponse> generateCall(Client client) {
      return client.create(CommonService.class).listImports();
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class createImport extends StreamRequest<CreateImportResponse> {
    private CreateImportRequest createImportRequest;

    @Override
    protected Call<CreateImportResponse> generateCall(Client client) {
      return client.create(CommonService.class).createImport(this.createImportRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class getImport extends StreamRequest<GetImportResponse> {
    private String id;

    @Override
    protected Call<GetImportResponse> generateCall(Client client) {
      return client.create(CommonService.class).getImport(this.id);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class getOG extends StreamRequest<GetOGResponse> {
    private String url;

    @Override
    protected Call<GetOGResponse> generateCall(Client client) {
      return client.create(CommonService.class).getOG(this.url);
    }
  }

  @lombok.NoArgsConstructor
  public static class listPermissions extends StreamRequest<ListPermissionsResponse> {

    @Override
    protected Call<ListPermissionsResponse> generateCall(Client client) {
      return client.create(CommonService.class).listPermissions();
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class getPermission extends StreamRequest<GetCustomPermissionResponse> {
    private String id;

    @Override
    protected Call<GetCustomPermissionResponse> generateCall(Client client) {
      return client.create(CommonService.class).getPermission(this.id);
    }
  }

  @lombok.NoArgsConstructor
  public static class listPushProviders extends StreamRequest<ListPushProvidersResponse> {

    @Override
    protected Call<ListPushProvidersResponse> generateCall(Client client) {
      return client.create(CommonService.class).listPushProviders();
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class upsertPushProvider extends StreamRequest<UpsertPushProviderResponse> {
    private UpsertPushProviderRequest upsertPushProviderRequest;

    @Override
    protected Call<UpsertPushProviderResponse> generateCall(Client client) {
      return client.create(CommonService.class).upsertPushProvider(this.upsertPushProviderRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class deletePushProvider extends StreamRequest<Response> {
    private String type;
    private String name;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).deletePushProvider(this.type, this.name);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class getRateLimits extends StreamRequest<GetRateLimitsResponse> {
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

  @lombok.NoArgsConstructor
  public static class listRoles extends StreamRequest<ListRolesResponse> {

    @Override
    protected Call<ListRolesResponse> generateCall(Client client) {
      return client.create(CommonService.class).listRoles();
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class createRole extends StreamRequest<CreateRoleResponse> {
    private CreateRoleRequest createRoleRequest;

    @Override
    protected Call<CreateRoleResponse> generateCall(Client client) {
      return client.create(CommonService.class).createRole(this.createRoleRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class deleteRole extends StreamRequest<Response> {
    private String name;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).deleteRole(this.name);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class getTask extends StreamRequest<GetTaskResponse> {
    private String id;

    @Override
    protected Call<GetTaskResponse> generateCall(Client client) {
      return client.create(CommonService.class).getTask(this.id);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class queryUsers extends StreamRequest<QueryUsersResponse> {
    private QueryUsersPayload payload;

    @Override
    protected Call<QueryUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).queryUsers(this.payload);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class updateUsersPartial extends StreamRequest<UpdateUsersResponse> {
    private UpdateUsersPartialRequest updateUsersPartialRequest;

    @Override
    protected Call<UpdateUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).updateUsersPartial(this.updateUsersPartialRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class updateUsers extends StreamRequest<UpdateUsersResponse> {
    private UpdateUsersRequest updateUsersRequest;

    @Override
    protected Call<UpdateUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).updateUsers(this.updateUsersRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class getBlockedUsers extends StreamRequest<GetBlockedUsersResponse> {
    private String userID;

    @Override
    protected Call<GetBlockedUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).getBlockedUsers(this.userID);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class blockUsers extends StreamRequest<BlockUsersResponse> {
    private BlockUsersRequest blockUsersRequest;

    @Override
    protected Call<BlockUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).blockUsers(this.blockUsersRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class deactivateUsers extends StreamRequest<DeactivateUsersResponse> {
    private DeactivateUsersRequest deactivateUsersRequest;

    @Override
    protected Call<DeactivateUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).deactivateUsers(this.deactivateUsersRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class deleteUsers extends StreamRequest<DeleteUsersResponse> {
    private DeleteUsersRequest deleteUsersRequest;

    @Override
    protected Call<DeleteUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).deleteUsers(this.deleteUsersRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class reactivateUsers extends StreamRequest<ReactivateUsersResponse> {
    private ReactivateUsersRequest reactivateUsersRequest;

    @Override
    protected Call<ReactivateUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).reactivateUsers(this.reactivateUsersRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class restoreUsers extends StreamRequest<Response> {
    private RestoreUsersRequest restoreUsersRequest;

    @Override
    protected Call<Response> generateCall(Client client) {
      return client.create(CommonService.class).restoreUsers(this.restoreUsersRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class unblockUsers extends StreamRequest<UnblockUsersResponse> {
    private UnblockUsersRequest unblockUsersRequest;

    @Override
    protected Call<UnblockUsersResponse> generateCall(Client client) {
      return client.create(CommonService.class).unblockUsers(this.unblockUsersRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class deactivateUser extends StreamRequest<DeactivateUserResponse> {
    private String userID;
    private DeactivateUserRequest deactivateUserRequest;

    @Override
    protected Call<DeactivateUserResponse> generateCall(Client client) {
      return client
          .create(CommonService.class)
          .deactivateUser(this.userID, this.deactivateUserRequest);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class exportUser extends StreamRequest<ExportUserResponse> {
    private String userID;

    @Override
    protected Call<ExportUserResponse> generateCall(Client client) {
      return client.create(CommonService.class).exportUser(this.userID);
    }
  }

  @lombok.NoArgsConstructor
  @lombok.AllArgsConstructor
  public static class reactivateUser extends StreamRequest<ReactivateUserResponse> {
    private String userID;
    private ReactivateUserRequest reactivateUserRequest;

    @Override
    protected Call<ReactivateUserResponse> generateCall(Client client) {
      return client
          .create(CommonService.class)
          .reactivateUser(this.userID, this.reactivateUserRequest);
    }
  }
}
