package io.getstream.services;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.services.framework.StreamRequest;
import io.getstream.services.framework.StreamSDKClient;
import java.util.*;
import org.jetbrains.annotations.NotNull;

public class CommonImpl implements Common {
  private StreamSDKClient client;

  public CommonImpl(StreamSDKClient client) {
    this.client = client;
  }

  @NotNull
  public StreamRequest<GetApplicationResponse> getApp(GetAppRequest request)
      throws StreamException {

    return new StreamRequest<GetApplicationResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/app",
        request,
        null);
  }

  @NotNull
  public StreamRequest<Response> updateApp(UpdateAppRequest request) throws StreamException {

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "PATCH",
        "/api/v2/app",
        request,
        null);
  }

  @NotNull
  public StreamRequest<ListBlockListResponse> listBlockLists(ListBlockListsRequest request)
      throws StreamException {

    return new StreamRequest<ListBlockListResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/blocklists",
        request,
        null);
  }

  @NotNull
  public StreamRequest<CreateBlockListResponse> createBlockList(CreateBlockListRequest request)
      throws StreamException {

    return new StreamRequest<CreateBlockListResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/blocklists",
        request,
        null);
  }

  @NotNull
  public StreamRequest<Response> deleteBlockList(
      @NotNull String name, DeleteBlockListRequest request) throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/blocklists/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetBlockListResponse> getBlockList(
      @NotNull String name, GetBlockListRequest request) throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<GetBlockListResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/blocklists/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpdateBlockListResponse> updateBlockList(
      @NotNull String name, UpdateBlockListRequest request) throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<UpdateBlockListResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "PUT",
        "/api/v2/blocklists/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<CheckPushResponse> checkPush(CheckPushRequest request)
      throws StreamException {

    return new StreamRequest<CheckPushResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/check_push",
        request,
        null);
  }

  @NotNull
  public StreamRequest<CheckSNSResponse> checkSNS(CheckSNSRequest request) throws StreamException {

    return new StreamRequest<CheckSNSResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/check_sns",
        request,
        null);
  }

  @NotNull
  public StreamRequest<CheckSQSResponse> checkSQS(CheckSQSRequest request) throws StreamException {

    return new StreamRequest<CheckSQSResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/check_sqs",
        request,
        null);
  }

  @NotNull
  public StreamRequest<Response> deleteDevice(DeleteDeviceRequest request) throws StreamException {

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/devices",
        request,
        null);
  }

  @NotNull
  public StreamRequest<ListDevicesResponse> listDevices(ListDevicesRequest request)
      throws StreamException {

    return new StreamRequest<ListDevicesResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/devices",
        request,
        null);
  }

  @NotNull
  public StreamRequest<Response> createDevice(CreateDeviceRequest request) throws StreamException {

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/devices",
        request,
        null);
  }

  @NotNull
  public StreamRequest<ExportUsersResponse> exportUsers(ExportUsersRequest request)
      throws StreamException {

    return new StreamRequest<ExportUsersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/export/users",
        request,
        null);
  }

  @NotNull
  public StreamRequest<ListExternalStorageResponse> listExternalStorage(
      ListExternalStorageRequest request) throws StreamException {

    return new StreamRequest<ListExternalStorageResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/external_storage",
        request,
        null);
  }

  @NotNull
  public StreamRequest<CreateExternalStorageResponse> createExternalStorage(
      CreateExternalStorageRequest request) throws StreamException {

    return new StreamRequest<CreateExternalStorageResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/external_storage",
        request,
        null);
  }

  @NotNull
  public StreamRequest<DeleteExternalStorageResponse> deleteExternalStorage(
      @NotNull String name, DeleteExternalStorageRequest request) throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<DeleteExternalStorageResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/external_storage/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<UpdateExternalStorageResponse> updateExternalStorage(
      @NotNull String name, UpdateExternalStorageRequest request) throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<UpdateExternalStorageResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "PUT",
        "/api/v2/external_storage/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<CheckExternalStorageResponse> checkExternalStorage(
      @NotNull String name, CheckExternalStorageRequest request) throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<CheckExternalStorageResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/external_storage/{name}/check",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<CreateGuestResponse> createGuest(CreateGuestRequest request)
      throws StreamException {

    return new StreamRequest<CreateGuestResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/guest",
        request,
        null);
  }

  @NotNull
  public StreamRequest<CreateImportURLResponse> createImportURL(CreateImportURLRequest request)
      throws StreamException {

    return new StreamRequest<CreateImportURLResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/import_urls",
        request,
        null);
  }

  @NotNull
  public StreamRequest<ListImportsResponse> listImports(ListImportsRequest request)
      throws StreamException {

    return new StreamRequest<ListImportsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/imports",
        request,
        null);
  }

  @NotNull
  public StreamRequest<CreateImportResponse> createImport(CreateImportRequest request)
      throws StreamException {

    return new StreamRequest<CreateImportResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/imports",
        request,
        null);
  }

  @NotNull
  public StreamRequest<GetImportResponse> getImport(@NotNull String id, GetImportRequest request)
      throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<GetImportResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/imports/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetOGResponse> getOG(GetOGRequest request) throws StreamException {

    return new StreamRequest<GetOGResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/og",
        request,
        null);
  }

  @NotNull
  public StreamRequest<ListPermissionsResponse> listPermissions(ListPermissionsRequest request)
      throws StreamException {

    return new StreamRequest<ListPermissionsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/permissions",
        request,
        null);
  }

  @NotNull
  public StreamRequest<GetCustomPermissionResponse> getPermission(
      @NotNull String id, GetPermissionRequest request) throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<GetCustomPermissionResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/permissions/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<ListPushProvidersResponse> listPushProviders(
      ListPushProvidersRequest request) throws StreamException {

    return new StreamRequest<ListPushProvidersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/push_providers",
        request,
        null);
  }

  @NotNull
  public StreamRequest<UpsertPushProviderResponse> upsertPushProvider(
      UpsertPushProviderRequest request) throws StreamException {

    return new StreamRequest<UpsertPushProviderResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/push_providers",
        request,
        null);
  }

  @NotNull
  public StreamRequest<Response> deletePushProvider(
      @NotNull String type, @NotNull String name, DeletePushProviderRequest request)
      throws StreamException {
    var pathParams =
        Map.of(
            "type", type,
            "name", name);

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/push_providers/{type}/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetRateLimitsResponse> getRateLimits(GetRateLimitsRequest request)
      throws StreamException {

    return new StreamRequest<GetRateLimitsResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/rate_limits",
        request,
        null);
  }

  @NotNull
  public StreamRequest<ListRolesResponse> listRoles(ListRolesRequest request)
      throws StreamException {

    return new StreamRequest<ListRolesResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/roles",
        request,
        null);
  }

  @NotNull
  public StreamRequest<CreateRoleResponse> createRole(CreateRoleRequest request)
      throws StreamException {

    return new StreamRequest<CreateRoleResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/roles",
        request,
        null);
  }

  @NotNull
  public StreamRequest<Response> deleteRole(@NotNull String name, DeleteRoleRequest request)
      throws StreamException {
    var pathParams = Map.of("name", name);

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "DELETE",
        "/api/v2/roles/{name}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<GetTaskResponse> getTask(@NotNull String id, GetTaskRequest request)
      throws StreamException {
    var pathParams = Map.of("id", id);

    return new StreamRequest<GetTaskResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/tasks/{id}",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<QueryUsersResponse> queryUsers(QueryUsersRequest request)
      throws StreamException {

    return new StreamRequest<QueryUsersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/users",
        request,
        null);
  }

  @NotNull
  public StreamRequest<UpdateUsersResponse> updateUsersPartial(UpdateUsersPartialRequest request)
      throws StreamException {

    return new StreamRequest<UpdateUsersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "PATCH",
        "/api/v2/users",
        request,
        null);
  }

  @NotNull
  public StreamRequest<UpdateUsersResponse> updateUsers(UpdateUsersRequest request)
      throws StreamException {

    return new StreamRequest<UpdateUsersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/users",
        request,
        null);
  }

  @NotNull
  public StreamRequest<GetBlockedUsersResponse> getBlockedUsers(GetBlockedUsersRequest request)
      throws StreamException {

    return new StreamRequest<GetBlockedUsersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/users/block",
        request,
        null);
  }

  @NotNull
  public StreamRequest<BlockUsersResponse> blockUsers(BlockUsersRequest request)
      throws StreamException {

    return new StreamRequest<BlockUsersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/users/block",
        request,
        null);
  }

  @NotNull
  public StreamRequest<DeactivateUsersResponse> deactivateUsers(DeactivateUsersRequest request)
      throws StreamException {

    return new StreamRequest<DeactivateUsersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/users/deactivate",
        request,
        null);
  }

  @NotNull
  public StreamRequest<DeleteUsersResponse> deleteUsers(DeleteUsersRequest request)
      throws StreamException {

    return new StreamRequest<DeleteUsersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/users/delete",
        request,
        null);
  }

  @NotNull
  public StreamRequest<ReactivateUsersResponse> reactivateUsers(ReactivateUsersRequest request)
      throws StreamException {

    return new StreamRequest<ReactivateUsersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/users/reactivate",
        request,
        null);
  }

  @NotNull
  public StreamRequest<Response> restoreUsers(RestoreUsersRequest request) throws StreamException {

    return new StreamRequest<Response>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/users/restore",
        request,
        null);
  }

  @NotNull
  public StreamRequest<UnblockUsersResponse> unblockUsers(UnblockUsersRequest request)
      throws StreamException {

    return new StreamRequest<UnblockUsersResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/users/unblock",
        request,
        null);
  }

  @NotNull
  public StreamRequest<DeactivateUserResponse> deactivateUser(
      @NotNull String userID, DeactivateUserRequest request) throws StreamException {
    var pathParams = Map.of("user_id", userID);

    return new StreamRequest<DeactivateUserResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/users/{user_id}/deactivate",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<ExportUserResponse> exportUser(
      @NotNull String userID, ExportUserRequest request) throws StreamException {
    var pathParams = Map.of("user_id", userID);

    return new StreamRequest<ExportUserResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "GET",
        "/api/v2/users/{user_id}/export",
        request,
        pathParams);
  }

  @NotNull
  public StreamRequest<ReactivateUserResponse> reactivateUser(
      @NotNull String userID, ReactivateUserRequest request) throws StreamException {
    var pathParams = Map.of("user_id", userID);

    return new StreamRequest<ReactivateUserResponse>(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        "POST",
        "/api/v2/users/{user_id}/reactivate",
        request,
        pathParams);
  }
}
