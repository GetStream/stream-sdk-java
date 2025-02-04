package io.getstream.services;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.services.framework.StreamRequest;
import io.getstream.services.framework.StreamHTTPClient;
import java.util.*;
import org.jetbrains.annotations.NotNull;

public class CommonImpl implements Common {
  private StreamHTTPClient client;

  public CommonImpl(StreamHTTPClient client) {
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

  public StreamRequest<GetApplicationResponse> getApp() throws StreamException {
    return getApp(new GetAppRequest());
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

  public StreamRequest<Response> updateApp() throws StreamException {
    return updateApp(new UpdateAppRequest());
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

  public StreamRequest<ListBlockListResponse> listBlockLists() throws StreamException {
    return listBlockLists(new ListBlockListsRequest());
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

  public StreamRequest<Response> deleteBlockList(@NotNull String name) throws StreamException {
    return deleteBlockList(name, new DeleteBlockListRequest());
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

  public StreamRequest<GetBlockListResponse> getBlockList(@NotNull String name)
      throws StreamException {
    return getBlockList(name, new GetBlockListRequest());
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

  public StreamRequest<UpdateBlockListResponse> updateBlockList(@NotNull String name)
      throws StreamException {
    return updateBlockList(name, new UpdateBlockListRequest());
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

  public StreamRequest<CheckPushResponse> checkPush() throws StreamException {
    return checkPush(new CheckPushRequest());
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

  public StreamRequest<CheckSNSResponse> checkSNS() throws StreamException {
    return checkSNS(new CheckSNSRequest());
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

  public StreamRequest<CheckSQSResponse> checkSQS() throws StreamException {
    return checkSQS(new CheckSQSRequest());
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

  public StreamRequest<ListDevicesResponse> listDevices() throws StreamException {
    return listDevices(new ListDevicesRequest());
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

  public StreamRequest<ListExternalStorageResponse> listExternalStorage() throws StreamException {
    return listExternalStorage(new ListExternalStorageRequest());
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

  public StreamRequest<DeleteExternalStorageResponse> deleteExternalStorage(@NotNull String name)
      throws StreamException {
    return deleteExternalStorage(name, new DeleteExternalStorageRequest());
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

  public StreamRequest<CheckExternalStorageResponse> checkExternalStorage(@NotNull String name)
      throws StreamException {
    return checkExternalStorage(name, new CheckExternalStorageRequest());
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

  public StreamRequest<CreateImportURLResponse> createImportURL() throws StreamException {
    return createImportURL(new CreateImportURLRequest());
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

  public StreamRequest<ListImportsResponse> listImports() throws StreamException {
    return listImports(new ListImportsRequest());
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

  public StreamRequest<GetImportResponse> getImport(@NotNull String id) throws StreamException {
    return getImport(id, new GetImportRequest());
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

  public StreamRequest<ListPermissionsResponse> listPermissions() throws StreamException {
    return listPermissions(new ListPermissionsRequest());
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

  public StreamRequest<GetCustomPermissionResponse> getPermission(@NotNull String id)
      throws StreamException {
    return getPermission(id, new GetPermissionRequest());
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

  public StreamRequest<ListPushProvidersResponse> listPushProviders() throws StreamException {
    return listPushProviders(new ListPushProvidersRequest());
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

  public StreamRequest<UpsertPushProviderResponse> upsertPushProvider() throws StreamException {
    return upsertPushProvider(new UpsertPushProviderRequest());
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

  public StreamRequest<Response> deletePushProvider(@NotNull String type, @NotNull String name)
      throws StreamException {
    return deletePushProvider(type, name, new DeletePushProviderRequest());
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

  public StreamRequest<GetRateLimitsResponse> getRateLimits() throws StreamException {
    return getRateLimits(new GetRateLimitsRequest());
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

  public StreamRequest<ListRolesResponse> listRoles() throws StreamException {
    return listRoles(new ListRolesRequest());
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

  public StreamRequest<Response> deleteRole(@NotNull String name) throws StreamException {
    return deleteRole(name, new DeleteRoleRequest());
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

  public StreamRequest<GetTaskResponse> getTask(@NotNull String id) throws StreamException {
    return getTask(id, new GetTaskRequest());
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

  public StreamRequest<QueryUsersResponse> queryUsers() throws StreamException {
    return queryUsers(new QueryUsersRequest());
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

  public StreamRequest<GetBlockedUsersResponse> getBlockedUsers() throws StreamException {
    return getBlockedUsers(new GetBlockedUsersRequest());
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

  public StreamRequest<DeactivateUserResponse> deactivateUser(@NotNull String userID)
      throws StreamException {
    return deactivateUser(userID, new DeactivateUserRequest());
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

  public StreamRequest<ExportUserResponse> exportUser(@NotNull String userID)
      throws StreamException {
    return exportUser(userID, new ExportUserRequest());
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

  public StreamRequest<ReactivateUserResponse> reactivateUser(@NotNull String userID)
      throws StreamException {
    return reactivateUser(userID, new ReactivateUserRequest());
  }
}
