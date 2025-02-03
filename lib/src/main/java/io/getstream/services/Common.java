package io.getstream.services;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.services.framework.StreamRequest;
import java.util.*;
import org.jetbrains.annotations.NotNull;

public interface Common {
  @NotNull
  public StreamRequest<GetApplicationResponse> getApp(GetAppRequest request) throws StreamException;

  @NotNull
  public StreamRequest<Response> updateApp(UpdateAppRequest request) throws StreamException;

  @NotNull
  public StreamRequest<ListBlockListResponse> listBlockLists(ListBlockListsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<CreateBlockListResponse> createBlockList(CreateBlockListRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<Response> deleteBlockList(
      @NotNull String name, DeleteBlockListRequest request) throws StreamException;

  @NotNull
  public StreamRequest<GetBlockListResponse> getBlockList(
      @NotNull String name, GetBlockListRequest request) throws StreamException;

  @NotNull
  public StreamRequest<UpdateBlockListResponse> updateBlockList(
      @NotNull String name, UpdateBlockListRequest request) throws StreamException;

  @NotNull
  public StreamRequest<CheckPushResponse> checkPush(CheckPushRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<CheckSNSResponse> checkSNS(CheckSNSRequest request) throws StreamException;

  @NotNull
  public StreamRequest<CheckSQSResponse> checkSQS(CheckSQSRequest request) throws StreamException;

  @NotNull
  public StreamRequest<Response> deleteDevice(DeleteDeviceRequest request) throws StreamException;

  @NotNull
  public StreamRequest<ListDevicesResponse> listDevices(ListDevicesRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<Response> createDevice(CreateDeviceRequest request) throws StreamException;

  @NotNull
  public StreamRequest<ExportUsersResponse> exportUsers(ExportUsersRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<ListExternalStorageResponse> listExternalStorage(
      ListExternalStorageRequest request) throws StreamException;

  @NotNull
  public StreamRequest<CreateExternalStorageResponse> createExternalStorage(
      CreateExternalStorageRequest request) throws StreamException;

  @NotNull
  public StreamRequest<DeleteExternalStorageResponse> deleteExternalStorage(
      @NotNull String name, DeleteExternalStorageRequest request) throws StreamException;

  @NotNull
  public StreamRequest<UpdateExternalStorageResponse> updateExternalStorage(
      @NotNull String name, UpdateExternalStorageRequest request) throws StreamException;

  @NotNull
  public StreamRequest<CheckExternalStorageResponse> checkExternalStorage(
      @NotNull String name, CheckExternalStorageRequest request) throws StreamException;

  @NotNull
  public StreamRequest<CreateGuestResponse> createGuest(CreateGuestRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<CreateImportURLResponse> createImportURL(CreateImportURLRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<ListImportsResponse> listImports(ListImportsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<CreateImportResponse> createImport(CreateImportRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<GetImportResponse> getImport(@NotNull String id, GetImportRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<GetOGResponse> getOG(GetOGRequest request) throws StreamException;

  @NotNull
  public StreamRequest<ListPermissionsResponse> listPermissions(ListPermissionsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<GetCustomPermissionResponse> getPermission(
      @NotNull String id, GetPermissionRequest request) throws StreamException;

  @NotNull
  public StreamRequest<ListPushProvidersResponse> listPushProviders(
      ListPushProvidersRequest request) throws StreamException;

  @NotNull
  public StreamRequest<UpsertPushProviderResponse> upsertPushProvider(
      UpsertPushProviderRequest request) throws StreamException;

  @NotNull
  public StreamRequest<Response> deletePushProvider(
      @NotNull String type, @NotNull String name, DeletePushProviderRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<GetRateLimitsResponse> getRateLimits(GetRateLimitsRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<ListRolesResponse> listRoles(ListRolesRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<CreateRoleResponse> createRole(CreateRoleRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<Response> deleteRole(@NotNull String name, DeleteRoleRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<GetTaskResponse> getTask(@NotNull String id, GetTaskRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<QueryUsersResponse> queryUsers(QueryUsersRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<UpdateUsersResponse> updateUsersPartial(UpdateUsersPartialRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<UpdateUsersResponse> updateUsers(UpdateUsersRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<GetBlockedUsersResponse> getBlockedUsers(GetBlockedUsersRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<BlockUsersResponse> blockUsers(BlockUsersRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<DeactivateUsersResponse> deactivateUsers(DeactivateUsersRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<DeleteUsersResponse> deleteUsers(DeleteUsersRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<ReactivateUsersResponse> reactivateUsers(ReactivateUsersRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<Response> restoreUsers(RestoreUsersRequest request) throws StreamException;

  @NotNull
  public StreamRequest<UnblockUsersResponse> unblockUsers(UnblockUsersRequest request)
      throws StreamException;

  @NotNull
  public StreamRequest<DeactivateUserResponse> deactivateUser(
      @NotNull String userID, DeactivateUserRequest request) throws StreamException;

  @NotNull
  public StreamRequest<ExportUserResponse> exportUser(
      @NotNull String userID, ExportUserRequest request) throws StreamException;

  @NotNull
  public StreamRequest<ReactivateUserResponse> reactivateUser(
      @NotNull String userID, ReactivateUserRequest request) throws StreamException;
}
