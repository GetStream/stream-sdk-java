package io.getstream.services;

import io.getstream.models.*;
import io.getstream.services.framework.StreamRequest;
import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CommonService {
  @GET("/api/v2/app")
  @NotNull
  StreamRequest<GetApplicationResponse> getApp();

  @PATCH("/api/v2/app")
  @NotNull
  StreamRequest<Response> updateApp(@Nullable @Body UpdateAppRequest updateAppRequest);

  @GET("/api/v2/blocklists")
  @NotNull
  StreamRequest<ListBlockListResponse> listBlockLists();

  @POST("/api/v2/blocklists")
  @NotNull
  StreamRequest<Response> createBlockList(
      @NotNull @Body CreateBlockListRequest createBlockListRequest);

  @DELETE("/api/v2/blocklists/{name}")
  @NotNull
  StreamRequest<Response> deleteBlockList(@NotNull @Path("name") String name);

  @GET("/api/v2/blocklists/{name}")
  @NotNull
  StreamRequest<GetBlockListResponse> getBlockList(@NotNull @Path("name") String name);

  @PUT("/api/v2/blocklists/{name}")
  @NotNull
  StreamRequest<Response> updateBlockList(
      @NotNull @Path("name") String name,
      @Nullable @Body UpdateBlockListRequest updateBlockListRequest);

  @POST("/api/v2/check_push")
  @NotNull
  StreamRequest<CheckPushResponse> checkPush(@Nullable @Body CheckPushRequest checkPushRequest);

  @POST("/api/v2/check_sns")
  @NotNull
  StreamRequest<CheckSNSResponse> checkSNS(@Nullable @Body CheckSNSRequest checkSNSRequest);

  @POST("/api/v2/check_sqs")
  @NotNull
  StreamRequest<CheckSQSResponse> checkSQS(@Nullable @Body CheckSQSRequest checkSQSRequest);

  @DELETE("/api/v2/devices")
  @NotNull
  StreamRequest<Response> deleteDevice(
      @NotNull @Query("id") String id, @Nullable @Query("user_id") String userID);

  @GET("/api/v2/devices")
  @NotNull
  StreamRequest<ListDevicesResponse> listDevices(@Nullable @Query("user_id") String userID);

  @POST("/api/v2/devices")
  @NotNull
  StreamRequest<Response> createDevice(@NotNull @Body CreateDeviceRequest createDeviceRequest);

  @POST("/api/v2/export/users")
  @NotNull
  StreamRequest<ExportUsersResponse> exportUsers(
      @NotNull @Body ExportUsersRequest exportUsersRequest);

  @GET("/api/v2/external_storage")
  @NotNull
  StreamRequest<ListExternalStorageResponse> listExternalStorage();

  @POST("/api/v2/external_storage")
  @NotNull
  StreamRequest<CreateExternalStorageResponse> createExternalStorage(
      @NotNull @Body CreateExternalStorageRequest createExternalStorageRequest);

  @DELETE("/api/v2/external_storage/{name}")
  @NotNull
  StreamRequest<DeleteExternalStorageResponse> deleteExternalStorage(
      @NotNull @Path("name") String name);

  @PUT("/api/v2/external_storage/{name}")
  @NotNull
  StreamRequest<UpdateExternalStorageResponse> updateExternalStorage(
      @NotNull @Path("name") String name,
      @NotNull @Body UpdateExternalStorageRequest updateExternalStorageRequest);

  @GET("/api/v2/external_storage/{name}/check")
  @NotNull
  StreamRequest<CheckExternalStorageResponse> checkExternalStorage(
      @NotNull @Path("name") String name);

  @POST("/api/v2/guest")
  @NotNull
  StreamRequest<CreateGuestResponse> createGuest(
      @NotNull @Body CreateGuestRequest createGuestRequest);

  @POST("/api/v2/import_urls")
  @NotNull
  StreamRequest<CreateImportURLResponse> createImportURL(
      @Nullable @Body CreateImportURLRequest createImportURLRequest);

  @GET("/api/v2/imports")
  @NotNull
  StreamRequest<ListImportsResponse> listImports();

  @POST("/api/v2/imports")
  @NotNull
  StreamRequest<CreateImportResponse> createImport(
      @NotNull @Body CreateImportRequest createImportRequest);

  @GET("/api/v2/imports/{id}")
  @NotNull
  StreamRequest<GetImportResponse> getImport(@NotNull @Path("id") String id);

  @GET("/api/v2/og")
  @NotNull
  StreamRequest<GetOGResponse> getOG(@NotNull @Query("url") String url);

  @GET("/api/v2/permissions")
  @NotNull
  StreamRequest<ListPermissionsResponse> listPermissions();

  @GET("/api/v2/permissions/{id}")
  @NotNull
  StreamRequest<GetCustomPermissionResponse> getPermission(@NotNull @Path("id") String id);

  @GET("/api/v2/push_providers")
  @NotNull
  StreamRequest<ListPushProvidersResponse> listPushProviders();

  @POST("/api/v2/push_providers")
  @NotNull
  StreamRequest<UpsertPushProviderResponse> upsertPushProvider(
      @Nullable @Body UpsertPushProviderRequest upsertPushProviderRequest);

  @DELETE("/api/v2/push_providers/{type}/{name}")
  @NotNull
  StreamRequest<Response> deletePushProvider(
      @NotNull @Path("type") String type, @NotNull @Path("name") String name);

  @GET("/api/v2/rate_limits")
  @NotNull
  StreamRequest<GetRateLimitsResponse> getRateLimits(
      @Nullable @Query("server_side") Boolean serverSide,
      @Nullable @Query("android") Boolean android,
      @Nullable @Query("ios") Boolean ios,
      @Nullable @Query("web") Boolean web,
      @Nullable @Query("endpoints") String endpoints);

  @GET("/api/v2/roles")
  @NotNull
  StreamRequest<ListRolesResponse> listRoles();

  @POST("/api/v2/roles")
  @NotNull
  StreamRequest<CreateRoleResponse> createRole(@NotNull @Body CreateRoleRequest createRoleRequest);

  @DELETE("/api/v2/roles/{name}")
  @NotNull
  StreamRequest<Response> deleteRole(@NotNull @Path("name") String name);

  @GET("/api/v2/tasks/{id}")
  @NotNull
  StreamRequest<GetTaskResponse> getTask(@NotNull @Path("id") String id);

  @GET("/api/v2/users")
  @NotNull
  StreamRequest<QueryUsersResponse> queryUsers(
      @Nullable @Query("payload") QueryUsersPayload payload);

  @PATCH("/api/v2/users")
  @NotNull
  StreamRequest<UpdateUsersResponse> updateUsersPartial(
      @NotNull @Body UpdateUsersPartialRequest updateUsersPartialRequest);

  @POST("/api/v2/users")
  @NotNull
  StreamRequest<UpdateUsersResponse> updateUsers(
      @NotNull @Body UpdateUsersRequest updateUsersRequest);

  @GET("/api/v2/users/block")
  @NotNull
  StreamRequest<GetBlockedUsersResponse> getBlockedUsers(@Nullable @Query("user_id") String userID);

  @POST("/api/v2/users/block")
  @NotNull
  StreamRequest<BlockUsersResponse> blockUsers(@NotNull @Body BlockUsersRequest blockUsersRequest);

  @POST("/api/v2/users/deactivate")
  @NotNull
  StreamRequest<DeactivateUsersResponse> deactivateUsers(
      @NotNull @Body DeactivateUsersRequest deactivateUsersRequest);

  @POST("/api/v2/users/delete")
  @NotNull
  StreamRequest<DeleteUsersResponse> deleteUsers(
      @NotNull @Body DeleteUsersRequest deleteUsersRequest);

  @POST("/api/v2/users/reactivate")
  @NotNull
  StreamRequest<ReactivateUsersResponse> reactivateUsers(
      @NotNull @Body ReactivateUsersRequest reactivateUsersRequest);

  @POST("/api/v2/users/restore")
  @NotNull
  StreamRequest<Response> restoreUsers(@NotNull @Body RestoreUsersRequest restoreUsersRequest);

  @POST("/api/v2/users/unblock")
  @NotNull
  StreamRequest<UnblockUsersResponse> unblockUsers(
      @NotNull @Body UnblockUsersRequest unblockUsersRequest);

  @POST("/api/v2/users/{user_id}/deactivate")
  @NotNull
  StreamRequest<DeactivateUserResponse> deactivateUser(
      @NotNull @Path("user_id") String userID,
      @Nullable @Body DeactivateUserRequest deactivateUserRequest);

  @GET("/api/v2/users/{user_id}/export")
  @NotNull
  StreamRequest<ExportUserResponse> exportUser(@NotNull @Path("user_id") String userID);

  @POST("/api/v2/users/{user_id}/reactivate")
  @NotNull
  StreamRequest<ReactivateUserResponse> reactivateUser(
      @NotNull @Path("user_id") String userID,
      @Nullable @Body ReactivateUserRequest reactivateUserRequest);
}
