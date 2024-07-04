package io.getstream.services;

import io.getstream.models.*;
import java.util.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
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
  Call<GetApplicationResponse> getApp();

  @PATCH("/api/v2/app")
  @NotNull
  Call<Response> updateApp(@Nullable @Body UpdateAppRequest updateAppRequest);

  @GET("/api/v2/blocklists")
  @NotNull
  Call<ListBlockListResponse> listBlockLists();

  @POST("/api/v2/blocklists")
  @NotNull
  Call<Response> createBlockList(@NotNull @Body CreateBlockListRequest createBlockListRequest);

  @DELETE("/api/v2/blocklists/{name}")
  @NotNull
  Call<Response> deleteBlockList(@NotNull @Path("name") String name);

  @GET("/api/v2/blocklists/{name}")
  @NotNull
  Call<GetBlockListResponse> getBlockList(@NotNull @Path("name") String name);

  @PUT("/api/v2/blocklists/{name}")
  @NotNull
  Call<Response> updateBlockList(
      @NotNull @Path("name") String name,
      @Nullable @Body UpdateBlockListRequest updateBlockListRequest);

  @POST("/api/v2/check_push")
  @NotNull
  Call<CheckPushResponse> checkPush(@Nullable @Body CheckPushRequest checkPushRequest);

  @POST("/api/v2/check_sns")
  @NotNull
  Call<CheckSNSResponse> checkSNS(@Nullable @Body CheckSNSRequest checkSNSRequest);

  @POST("/api/v2/check_sqs")
  @NotNull
  Call<CheckSQSResponse> checkSQS(@Nullable @Body CheckSQSRequest checkSQSRequest);

  @DELETE("/api/v2/devices")
  @NotNull
  Call<Response> deleteDevice(
      @NotNull @Query("id") String id, @Nullable @Query("user_id") String userId);

  @GET("/api/v2/devices")
  @NotNull
  Call<ListDevicesResponse> listDevices(@Nullable @Query("user_id") String userId);

  @POST("/api/v2/devices")
  @NotNull
  Call<Response> createDevice(@NotNull @Body CreateDeviceRequest createDeviceRequest);

  @POST("/api/v2/export/users")
  @NotNull
  Call<ExportUsersResponse> exportUsers(@NotNull @Body ExportUsersRequest exportUsersRequest);

  @GET("/api/v2/external_storage")
  @NotNull
  Call<ListExternalStorageResponse> listExternalStorage();

  @POST("/api/v2/external_storage")
  @NotNull
  Call<CreateExternalStorageResponse> createExternalStorage(
      @NotNull @Body CreateExternalStorageRequest createExternalStorageRequest);

  @DELETE("/api/v2/external_storage/{name}")
  @NotNull
  Call<DeleteExternalStorageResponse> deleteExternalStorage(@NotNull @Path("name") String name);

  @PUT("/api/v2/external_storage/{name}")
  @NotNull
  Call<UpdateExternalStorageResponse> updateExternalStorage(
      @NotNull @Path("name") String name,
      @NotNull @Body UpdateExternalStorageRequest updateExternalStorageRequest);

  @GET("/api/v2/external_storage/{name}/check")
  @NotNull
  Call<CheckExternalStorageResponse> checkExternalStorage(@NotNull @Path("name") String name);

  @POST("/api/v2/guest")
  @NotNull
  Call<CreateGuestResponse> createGuest(@NotNull @Body CreateGuestRequest createGuestRequest);

  @POST("/api/v2/import_urls")
  @NotNull
  Call<CreateImportURLResponse> createImportURL(
      @Nullable @Body CreateImportURLRequest createImportURLRequest);

  @GET("/api/v2/imports")
  @NotNull
  Call<ListImportsResponse> listImports();

  @POST("/api/v2/imports")
  @NotNull
  Call<CreateImportResponse> createImport(@NotNull @Body CreateImportRequest createImportRequest);

  @GET("/api/v2/imports/{id}")
  @NotNull
  Call<GetImportResponse> getImport(@NotNull @Path("id") String id);

  @DELETE("/api/v2/moderation/ban")
  @NotNull
  Call<Response> unban(
      @NotNull @Query("target_user_id") String targetUserId,
      @Nullable @Query("channel_cid") String channelCid,
      @Nullable @Query("created_by") String createdBy);

  @POST("/api/v2/moderation/ban")
  @NotNull
  Call<Response> ban(@NotNull @Body BanRequest banRequest);

  @POST("/api/v2/moderation/flag")
  @NotNull
  Call<FlagResponse> flag(@Nullable @Body FlagRequest flagRequest);

  @POST("/api/v2/moderation/mute")
  @NotNull
  Call<MuteUserResponse> muteUser(@NotNull @Body MuteUserRequest muteUserRequest);

  @POST("/api/v2/moderation/unmute")
  @NotNull
  Call<UnmuteResponse> unmuteUser(@NotNull @Body UnmuteUserRequest unmuteUserRequest);

  @GET("/api/v2/og")
  @NotNull
  Call<GetOGResponse> getOG(@NotNull @Query("url") String url);

  @GET("/api/v2/permissions")
  @NotNull
  Call<ListPermissionsResponse> listPermissions();

  @GET("/api/v2/permissions/{id}")
  @NotNull
  Call<GetCustomPermissionResponse> getPermission(@NotNull @Path("id") String id);

  @GET("/api/v2/push_providers")
  @NotNull
  Call<ListPushProvidersResponse> listPushProviders();

  @POST("/api/v2/push_providers")
  @NotNull
  Call<UpsertPushProviderResponse> upsertPushProvider(
      @Nullable @Body UpsertPushProviderRequest upsertPushProviderRequest);

  @DELETE("/api/v2/push_providers/{type}/{name}")
  @NotNull
  Call<Response> deletePushProvider(
      @NotNull @Path("type") String type, @NotNull @Path("name") String name);

  @GET("/api/v2/rate_limits")
  @NotNull
  Call<GetRateLimitsResponse> getRateLimits(
      @Nullable @Query("server_side") Boolean serverSide,
      @Nullable @Query("android") Boolean android,
      @Nullable @Query("ios") Boolean ios,
      @Nullable @Query("web") Boolean web,
      @Nullable @Query("endpoints") String endpoints);

  @GET("/api/v2/roles")
  @NotNull
  Call<ListRolesResponse> listRoles();

  @POST("/api/v2/roles")
  @NotNull
  Call<CreateRoleResponse> createRole(@NotNull @Body CreateRoleRequest createRoleRequest);

  @DELETE("/api/v2/roles/{name}")
  @NotNull
  Call<Response> deleteRole(@NotNull @Path("name") String name);

  @GET("/api/v2/tasks/{id}")
  @NotNull
  Call<GetTaskResponse> getTask(@NotNull @Path("id") String id);

  @GET("/api/v2/users")
  @NotNull
  Call<QueryUsersResponse> queryUsers(@Nullable @Query("payload") QueryUsersPayload payload);

  @PATCH("/api/v2/users")
  @NotNull
  Call<UpdateUsersResponse> updateUsersPartial(
      @NotNull @Body UpdateUsersPartialRequest updateUsersPartialRequest);

  @POST("/api/v2/users")
  @NotNull
  Call<UpdateUsersResponse> updateUsers(@NotNull @Body UpdateUsersRequest updateUsersRequest);

  @GET("/api/v2/users/block")
  @NotNull
  Call<GetBlockedUsersResponse> getBlockedUsers(@Nullable @Query("user_id") String userId);

  @POST("/api/v2/users/block")
  @NotNull
  Call<BlockUsersResponse> blockUsers(@NotNull @Body BlockUsersRequest blockUsersRequest);

  @POST("/api/v2/users/deactivate")
  @NotNull
  Call<DeactivateUsersResponse> deactivateUsers(
      @NotNull @Body DeactivateUsersRequest deactivateUsersRequest);

  @POST("/api/v2/users/delete")
  @NotNull
  Call<DeleteUsersResponse> deleteUsers(@NotNull @Body DeleteUsersRequest deleteUsersRequest);

  @POST("/api/v2/users/reactivate")
  @NotNull
  Call<ReactivateUsersResponse> reactivateUsers(
      @NotNull @Body ReactivateUsersRequest reactivateUsersRequest);

  @POST("/api/v2/users/restore")
  @NotNull
  Call<Response> restoreUsers(@NotNull @Body RestoreUsersRequest restoreUsersRequest);

  @POST("/api/v2/users/unblock")
  @NotNull
  Call<UnblockUsersResponse> unblockUsers(@NotNull @Body UnblockUsersRequest unblockUsersRequest);

  @POST("/api/v2/users/{user_id}/deactivate")
  @NotNull
  Call<DeactivateUserResponse> deactivateUser(
      @NotNull @Path("user_id") String userId,
      @Nullable @Body DeactivateUserRequest deactivateUserRequest);

  @GET("/api/v2/users/{user_id}/export")
  @NotNull
  Call<ExportUserResponse> exportUser(@NotNull @Path("user_id") String userId);

  @POST("/api/v2/users/{user_id}/reactivate")
  @NotNull
  Call<ReactivateUserResponse> reactivateUser(
      @NotNull @Path("user_id") String userId,
      @Nullable @Body ReactivateUserRequest reactivateUserRequest);
}
