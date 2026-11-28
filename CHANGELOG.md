# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

## [11.4.0](https://github.com/GetStream/stream-sdk-java/compare/11.3.0...11.4.0) (2026-11-28)


### Features

* add analytics, experiment to ForYouConfig ([680da52](https://github.com/GetStream/stream-sdk-java/commit/680da522d91dd3a33b97c731822e6d656e4c14f6))
* add audioConfig to AIVideoConfigRequest ([3c84779](https://github.com/GetStream/stream-sdk-java/commit/3c84779b898407b478b901c047e1b73e189ddabf))
* add audioConfig to AIVideoConfigResponse ([3c84779](https://github.com/GetStream/stream-sdk-java/commit/3c84779b898407b478b901c047e1b73e189ddabf))
* add budgets to GetRateLimitsResponse ([2a1a896](https://github.com/GetStream/stream-sdk-java/commit/2a1a89608962ea60a2ae4cdf6275d3611e1f2234))
* add capped, total to QueryAppealsResponse ([cb76e14](https://github.com/GetStream/stream-sdk-java/commit/cb76e147d889c4426a65e0f3e7a72669b26f05e7))
* add channelCids, messagesFrom, messagesTo to ExportUsersRequest ([d8ec7ae](https://github.com/GetStream/stream-sdk-java/commit/d8ec7ae52b357eedc9dbafbc72a5a0867aea1eda))
* add channelType to BanRequest ([a84669d](https://github.com/GetStream/stream-sdk-java/commit/a84669df749fcc7389a3df38017b7d318b7dffff))
* add channelType to unban ([a84669d](https://github.com/GetStream/stream-sdk-java/commit/a84669df749fcc7389a3df38017b7d318b7dffff))
* add createSystemFeeds to AddActivityRequest ([231eba8](https://github.com/GetStream/stream-sdk-java/commit/231eba8c298443f4a333fbdc73e8982bdcd5c6be))
* add createSystemFeeds to UpsertActivitiesRequest ([231eba8](https://github.com/GetStream/stream-sdk-java/commit/231eba8c298443f4a333fbdc73e8982bdcd5c6be))
* add deletedBy to ChatMessageResponse ([231eba8](https://github.com/GetStream/stream-sdk-java/commit/231eba8c298443f4a333fbdc73e8982bdcd5c6be))
* add deletedBy to MessageResponse ([231eba8](https://github.com/GetStream/stream-sdk-java/commit/231eba8c298443f4a333fbdc73e8982bdcd5c6be))
* add deletedBy to MessageWithChannelResponse ([231eba8](https://github.com/GetStream/stream-sdk-java/commit/231eba8c298443f4a333fbdc73e8982bdcd5c6be))
* add deletedBy to SearchResultMessage ([231eba8](https://github.com/GetStream/stream-sdk-java/commit/231eba8c298443f4a333fbdc73e8982bdcd5c6be))
* add disableReviewQueue to ModerationDashboardPreferences ([0d8c6ea](https://github.com/GetStream/stream-sdk-java/commit/0d8c6eae5b3257f4c4e382d8d1cd2e11b7e83c45))
* add filterConditions to FeedsPolicyConfig ([680da52](https://github.com/GetStream/stream-sdk-java/commit/680da522d91dd3a33b97c731822e6d656e4c14f6))
* add filterConditions to MarkChannelsReadRequest ([3c84779](https://github.com/GetStream/stream-sdk-java/commit/3c84779b898407b478b901c047e1b73e189ddabf))
* add filterConditions to SetFeedsRetentionPolicyRequest ([680da52](https://github.com/GetStream/stream-sdk-java/commit/680da522d91dd3a33b97c731822e6d656e4c14f6))
* add includeCount to QueryAppealsRequest ([cb76e14](https://github.com/GetStream/stream-sdk-java/commit/cb76e147d889c4426a65e0f3e7a72669b26f05e7))
* add itemID to SubmitActionResponse ([000d9ab](https://github.com/GetStream/stream-sdk-java/commit/000d9ab2303ac893b97d6364902e39ed9b6f89ed))
* add limit, next to getBlockedUsers ([3c84779](https://github.com/GetStream/stream-sdk-java/commit/3c84779b898407b478b901c047e1b73e189ddabf))
* add messageSchema to AppResponseFields ([680da52](https://github.com/GetStream/stream-sdk-java/commit/680da522d91dd3a33b97c731822e6d656e4c14f6))
* add messageSchema to UpdateAppRequest ([680da52](https://github.com/GetStream/stream-sdk-java/commit/680da522d91dd3a33b97c731822e6d656e4c14f6))
* add next to GetBlockedUsersResponse ([3c84779](https://github.com/GetStream/stream-sdk-java/commit/3c84779b898407b478b901c047e1b73e189ddabf))
* add queryFeedsEngagementStats ([680da52](https://github.com/GetStream/stream-sdk-java/commit/680da522d91dd3a33b97c731822e6d656e4c14f6))
* add requestID to ActivityFeedbackRequest ([680da52](https://github.com/GetStream/stream-sdk-java/commit/680da522d91dd3a33b97c731822e6d656e4c14f6))
* add transcribeMessage ([0d8c6ea](https://github.com/GetStream/stream-sdk-java/commit/0d8c6eae5b3257f4c4e382d8d1cd2e11b7e83c45))


### Bug Fixes

* **security:** scope and expire SRT ingest tokens [SEC-2523] ([#153](https://github.com/GetStream/stream-sdk-java/issues/153)) ([1272aac](https://github.com/GetStream/stream-sdk-java/commit/1272aacdc372cc6b12bb0aed9251db3f599d7061))

## [11.3.0](https://github.com/GetStream/stream-sdk-java/compare/11.2.0...11.3.0) (2026-11-23)


### Features

* add `verifyAndParseSns` to verify the `signature` attribute on SNS HTTP(S) deliveries before parsing ([2758291](https://github.com/GetStream/stream-sdk-java/commit/2758291c7b0fdacf2ee1c47e398aa6f2212416bf))
* add createDraft ([35a61c7](https://github.com/GetStream/stream-sdk-java/commit/35a61c7f39a05a2da0a5acc68c2d87be36cdf20f))
* add createMessageMark ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add deleteScheduledActivity ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add excludeChannelCids to DeleteUsersRequest ([44dc3e5](https://github.com/GetStream/stream-sdk-java/commit/44dc3e56dca62e22686b909125646816f00ffa4d))
* add getHookEvents ([35a61c7](https://github.com/GetStream/stream-sdk-java/commit/35a61c7f39a05a2da0a5acc68c2d87be36cdf20f))
* add getScheduledActivity ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add getTrendingTopics ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add listPromotedTopics ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add markGroups, ownMarks to ChatMessageResponse ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add markGroups, ownMarks to MessageResponse ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add markGroups, ownMarks to MessageWithChannelResponse ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add markGroups, ownMarks to SearchResultMessage ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add markTypes, voiceTranscription to ChannelConfig ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add markTypes, voiceTranscription to ChannelConfigWithInfo ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add markTypes, voiceTranscription to ChannelTypeConfig ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add markTypes, voiceTranscription to CreateChannelTypeRequest ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add markTypes, voiceTranscription to CreateChannelTypeResponse ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add markTypes, voiceTranscription to GetChannelTypeResponse ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add markTypes, voiceTranscription to UpdateChannelTypeRequest ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add markTypes, voiceTranscription to UpdateChannelTypeResponse ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add memberCount, members to ScheduledMessageChannelResponse ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add messageMarks to AppResponseFields ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add messageMarks to UpdateAppRequest ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add ocrRules, ocrSeverityRules to AIVideoConfig ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add ocrRules, ocrSeverityRules to AIVideoConfigRequest ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add ocrRules, ocrSeverityRules to AIVideoConfigResponse ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add promoteTopics ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add publishScheduledActivityNow ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add queryFeedsReactions ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add queryMessageMarks ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add queryScheduledActivities ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add quotedMessage to ScheduledMessageContentResponse ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add scheduleActivity ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add skipFeedCreation to GetOrCreateFeedRequest ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add softUnread to NotificationMarkUnreadEvent ([35a61c7](https://github.com/GetStream/stream-sdk-java/commit/35a61c7f39a05a2da0a5acc68c2d87be36cdf20f))
* add softUnread to ReadStateResponse ([35a61c7](https://github.com/GetStream/stream-sdk-java/commit/35a61c7f39a05a2da0a5acc68c2d87be36cdf20f))
* add softUnread to UnreadCountsChannel ([35a61c7](https://github.com/GetStream/stream-sdk-java/commit/35a61c7f39a05a2da0a5acc68c2d87be36cdf20f))
* add trending to ForYouConfig ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add unpromoteTopics ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add updateScheduledActivity ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add validate to CreateBlockListRequest ([35a61c7](https://github.com/GetStream/stream-sdk-java/commit/35a61c7f39a05a2da0a5acc68c2d87be36cdf20f))
* add validate to UpdateBlockListRequest ([35a61c7](https://github.com/GetStream/stream-sdk-java/commit/35a61c7f39a05a2da0a5acc68c2d87be36cdf20f))
* add voiceTranscript to Attachment ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add voiceTranscript to GetOGResponse ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add voiceTranscription to ChannelConfigOverrides ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add voiceTranscription to ConfigOverridesRequest ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add webhook event ScheduledActivityFailedEvent ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))
* add webhook event ScheduledActivityPublishedEvent ([900a0fb](https://github.com/GetStream/stream-sdk-java/commit/900a0fbaffbd98b18d98223975e85faa4c424a4b))


### Bug Fixes

* `TokenBuilder.createToken` rejects a null, empty or blank `userId` instead of minting a server-side token ([2758291](https://github.com/GetStream/stream-sdk-java/commit/2758291c7b0fdacf2ee1c47e398aa6f2212416bf))
* `Webhook.verifySignature` returns false for a null or empty secret or signature instead of throwing ([2758291](https://github.com/GetStream/stream-sdk-java/commit/2758291c7b0fdacf2ee1c47e398aa6f2212416bf))

## [11.2.0](https://github.com/GetStream/stream-sdk-java/compare/11.1.0...11.2.0) (2026-11-13)


### Features

* add agent_recommendation to ActionLogResponse and SubmitActionRequest ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add agent_review to AppResponseFields, UpdateAppRequest, GetReviewQueueItemResponse and ReviewQueueItemResponse ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add ban_from_future_channels to UserBannedEvent ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add channel_type to RetentionRunResponse ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add channel_type to SetRetentionPolicyRequest and DeleteRetentionPolicyRequest ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add channel_type_rule to SetRetentionPolicyResponse and channel_type_rules to GetRetentionPolicyResponse ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add countReviewQueue ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add custom_format to ImportV2TaskSettings ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add diversify to ArrangeConfig ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add dwell and exploration to ForYouConfig ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add dwell_ms, position and request_id to TrackActivityMetricsEvent ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add effective_agent_review to AppResponseFields ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add expected_value to CallCustomPropertyParameters, ContentCustomPropertyParameters, ContentCustomPropertyCountParameters and UserCustomPropertyParameters ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add extra_opts to CallSettingsResponse ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add friend_type, max_friends, min_friends and reaction_types to ActivitySelectorConfig and ActivitySelectorConfigResponse ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add friends_engaged to ActivitySelectorType ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add getAgentReviewStatus ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add getConfigVersion, listConfigVersions, saveConfigVersion and publishConfigVersion ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add getNotificationStatus ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add inactive-channels-by-type to RetentionPolicyType ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add include_soft_deleted_channel to GetChannel ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add item_count_capped and item_count_pending to ModerationQueueResponse ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add limit and offset to ListImports ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add limit and offset to ListPolicyTestSets ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add listAgentFeedback ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add matched_blocklists to ModerationRulesTriggeredEvent ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add moderation_payload to ModerationCheckCompletedEvent ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add name to V2DeleteTemplate ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add position to ActivityResponse ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add published_version to ConfigResponse and ModerationConfig ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add reasoning_effort and strictness to ModerationEngineConfigRequest and ModerationEngineConfigResponse ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add remove_future_channels_ban to Unban ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add request_id to GetOrCreateFeedResponse ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add restore_shadowed_messages to UnbanActionRequestPayload ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add searchAll ([2e96d7e](https://github.com/GetStream/stream-sdk-java/commit/2e96d7e0d8ba65f29172b96d5712a62675abb3f0))
* add severities to TextRuleParameters, TextContentParameters, OCRContentParameters, IPFlagCountRuleParameters and ClosedCaptionRuleParameters ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add skip_viewer_visibility to GetActivity ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* add submitAgentFeedback ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))
* deprecate sqs_role_arn and sns_role_arn on EventHook ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))


### Bug Fixes

* limit decompressed webhook payloads to 10 MiB, configurable with Webhook.maxPayloadBytes ([7dbadec](https://github.com/GetStream/stream-sdk-java/commit/7dbadec39324a38cc8549d4eee7ae459d755c722))

## [11.1.0](https://github.com/GetStream/stream-sdk-java/compare/11.0.0...11.1.0) (2026-11-06)


### Features

* add activity_ids, include_deleted to ExportFeedsByTypeRequest ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add cancelExportFeeds ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add channel_cid, matched_labels, severity to ModerationRulesTriggeredEvent ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add country_codes to AnalyzeRequest ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add country_codes to CheckRequest ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add country_codes to ModerationPayload ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add country_codes to ReviewQueueItemResponse ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add deleteHistoryWindowTeams ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add engine to ConfigResponse ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add engine to UpsertConfigRequest ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add explicit_urls_only to BlockListConfig ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add failed_reason, oldest_source_message_id, truncated to ChannelForkResponse ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add failed_reason, oldest_source_message_id, truncated to ChannelForkSummary ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add fork to ChannelResponse ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add getHistoryWindowTeams ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add include_deleted, types to ExportFeedsAppRequest ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add include_reasons to EnrichmentOptions ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add language, translated to CallTranscription ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add ocr_severity_rules to AIImageConfig ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add queryDeletedChannels ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add reason to MarkReviewedRequestPayload ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add reason to RestoreActionRequestPayload ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add reasons to ActivityResponse ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add setHistoryWindowTeams ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add viewer_location_radius_km to ActivitySelectorConfig ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add viewer_location_radius_km to ActivitySelectorConfigResponse ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))
* add webhook event AsyncExportFeedsSuccessEvent ([f81c6da](https://github.com/GetStream/stream-sdk-java/commit/f81c6da8240c1811d8474584ee6ebb424a3f29ed))

## [11.0.0](https://github.com/GetStream/stream-sdk-java/compare/10.5.0...11.0.0) (2026-10-29)


### ⚠ BREAKING CHANGES

* [FLU-1105] regenerate client from openapi-v239.93.2 ([#120](https://github.com/GetStream/stream-sdk-java/issues/120))

### Features

* [FLU-1105] regenerate client from openapi-v239.93.2 ([#120](https://github.com/GetStream/stream-sdk-java/issues/120)) ([52fe52d](https://github.com/GetStream/stream-sdk-java/commit/52fe52db4501401e803acd10b3fc4bb819db3b4d))

## [10.5.0](https://github.com/GetStream/stream-sdk-java/compare/10.4.0...10.5.0) (2026-10-15)


### Features

* add `degraded_selectors` to the get-or-create feed response ([a7fb02c](https://github.com/GetStream/stream-sdk-java/commit/a7fb02c585133050e670bc37d3109abf4188220a))
* add `time_budget_ms` to activity selector configs ([a7fb02c](https://github.com/GetStream/stream-sdk-java/commit/a7fb02c585133050e670bc37d3109abf4188220a))

## [10.4.0](https://github.com/GetStream/stream-sdk-java/compare/10.3.0...10.4.0) (2026-10-09)


### Features

* publish releases to the Stream Maven repository (https://stream-io-repo.com); add it to your build repositories to get this and later versions ([693d019](https://github.com/GetStream/stream-sdk-java/commit/693d01999f8daec11728dca5d56bdcad7e6421a8))

## [10.3.0](https://github.com/GetStream/stream-sdk-java/compare/10.2.0...10.3.0) (2026-10-01)


### Features

* add `include_top_level_comment_count` to `getActivity` and `getComments`, returning `top_level_comment_count`, plus `comment_count` on `getComments` ([7f6d248](https://github.com/GetStream/stream-sdk-java/commit/7f6d2481e7719638a67eb2fc7dbf679342263697))
* add `ranking_candidate_limit` to activity selector configs ([7f6d248](https://github.com/GetStream/stream-sdk-java/commit/7f6d2481e7719638a67eb2fc7dbf679342263697))
* add moderation intent detection: `intent_config` on moderation configs and intent scores on check responses ([7f6d248](https://github.com/GetStream/stream-sdk-java/commit/7f6d2481e7719638a67eb2fc7dbf679342263697))
* add the `import.success` and `import.error` webhook events ([7f6d248](https://github.com/GetStream/stream-sdk-java/commit/7f6d2481e7719638a67eb2fc7dbf679342263697))


### Bug Fixes

* report this SDK's own version in `X-Stream-Client` when stream-chat-java is also on the classpath ([fdbd00b](https://github.com/GetStream/stream-sdk-java/commit/fdbd00bb2feeec9f4f368dc8c200e9695d7834f1))


### Performance Improvements

* raise the default connection pool size from 5 to 100 ([#113](https://github.com/GetStream/stream-sdk-java/issues/113)) ([e7afcf8](https://github.com/GetStream/stream-sdk-java/commit/e7afcf84badd6dea5ce7396c18d00b1bbba3f77f))

## [10.2.0](https://github.com/GetStream/stream-sdk-java/compare/10.1.1...10.2.0) (2026-09-28)


### Features

* [FEEDS-1963] regenerate from OpenAPI ([#106](https://github.com/GetStream/stream-sdk-java/issues/106)) ([0af93a1](https://github.com/GetStream/stream-sdk-java/commit/0af93a1dafe806f88a92333de6e2c289a06806e0))

### [10.1.1](https://github.com/GetStream/stream-sdk-java/compare/10.1.0...10.1.1) (2026-09-15)

## [10.1.0](https://github.com/GetStream/stream-sdk-java/compare/10.0.0...10.1.0) (2026-09-10)


### Features

* [FEEDS-1763.1] regenerate OpenAPI client ([c0612ad](https://github.com/GetStream/stream-sdk-java/commit/c0612adf4ad7aafa603cf7414f87682f0d4f079f)), closes [#16794](https://github.com/GetStream/stream-sdk-java/issues/16794)

## [10.0.0](https://github.com/GetStream/stream-sdk-java/compare/9.4.0...10.0.0) (2026-09-03)


### Features

* [CHA-4947] regenerate from latest OpenAPI ([814c425](https://github.com/GetStream/stream-sdk-java/commit/814c42588faf9c6427872150c0d926f435b097d0))
* [FEEDS-1830] regenerate OpenAPI client ([d52df07](https://github.com/GetStream/stream-sdk-java/commit/d52df07f5092fa2228e0e44b6a4510a803d586d0))


### Bug Fixes

* [FEEDS-1830] accept follow with feed_member_viewer role ([d76c07e](https://github.com/GetStream/stream-sdk-java/commit/d76c07e021fcc3e7cf441a6e878ff484470d67f5))
* [FEEDS-1830] read reminder from CreateReminderResponse wrapper ([204bc86](https://github.com/GetStream/stream-sdk-java/commit/204bc86c0d3bc2fe6e4d00585be4b4996d698ed0))

## [9.4.0](https://github.com/GetStream/stream-sdk-java/compare/9.3.0...9.4.0) (2026-08-17)

## [9.3.0](https://github.com/GetStream/stream-sdk-java/compare/9.2.0...9.3.0) (2026-08-12)


### Bug Fixes

* name the HTTP status when the error body is not JSON ([#79](https://github.com/GetStream/stream-sdk-java/issues/79)) ([a49641c](https://github.com/GetStream/stream-sdk-java/commit/a49641c1d01074149afa62ce6d1f021443f05695))

## [9.2.0](https://github.com/GetStream/stream-sdk-java/compare/9.1.0...9.2.0) (2026-08-05)


### Bug Fixes

* **ci:** align workflow token permissions with actual operations ([59c1706](https://github.com/GetStream/stream-sdk-java/commit/59c170648ba257deb1d8b5bb4ad6c671a33b3922))

## [9.1.0](https://github.com/GetStream/stream-sdk-java/compare/9.0.0...9.1.0) (2026-07-24)


### Features

* add opt-in retry for rate-limited and transport-failed requests ([#75](https://github.com/GetStream/stream-sdk-java/issues/75)) ([e4ea451](https://github.com/GetStream/stream-sdk-java/commit/e4ea4517bb4d29b7caa773ea8ac1f6de91feedb2))
* add slf4j structured logging with secret redaction ([#73](https://github.com/GetStream/stream-sdk-java/issues/73)) ([88ad7c9](https://github.com/GetStream/stream-sdk-java/commit/88ad7c97f5c25cc844493dfcc0c24129c5489918))

## [9.0.0](https://github.com/GetStream/stream-sdk-java/compare/8.0.0...9.0.0) (2026-07-15)


### ⚠ BREAKING CHANGES

* renames moderation CallResponse to ModerationCallResponse (video CallResponse keeps its name); drops non-functional internal ModerationSettings fields no longer in the spec.

### Features

* add GetChannel endpoint and rename shadowed moderation schema ([#70](https://github.com/GetStream/stream-sdk-java/issues/70)) ([3ecf42c](https://github.com/GetStream/stream-sdk-java/commit/3ecf42ca121675321fbd409c0067c0047e1387a7))

## [8.0.0](https://github.com/GetStream/stream-sdk-java/compare/7.2.0...8.0.0) (2026-06-23)


### ⚠ BREAKING CHANGES

* source- and binary-incompatible for Java consumers.
Moderation.flag(...) now returns StreamRequest<FlagItemResponse> (was
FlagResponse); the flag-action acknowledgement (itemID, duration) moved to the
new FlagItemResponse and FlagResponse now models the full flag record. Removed
getters FlagResponse.getItemID()/getDuration() and FlagDetails.getExtra();
changed getter return types on ChannelInput/ChannelDataUpdate.getConfigOverrides(),
FlagDetails.getAutomod(), and ChatMessageResponse.getAttachments()/getOwnReactions()/
getLatestReactions(). Warrants a major version bump.

The version bump itself is handled at release time by initiate_release
(workflow_dispatch version input -> gradle.properties + standard-version),
so no version files are touched here.

* test: update ModerationTest for regenerated flag/ban return types

flag() now returns FlagItemResponse and ban() returns ModerationBanResponse
after the OpenAPI regeneration. Update the hand-maintained ModerationTest
assignments accordingly, and add the ban() return-type change to the CHANGELOG
breaking list (it was missed in the first pass).

### Features

* [CHA-2958] typed error hierarchy + waitForTask ([#65](https://github.com/GetStream/stream-sdk-java/issues/65)) ([1b314d4](https://github.com/GetStream/stream-sdk-java/commit/1b314d46d875f5377dc96344e93bdf7a8d68b798))
* regenerate SDK from latest chat OpenAPI spec ([#66](https://github.com/GetStream/stream-sdk-java/issues/66)) ([ae91ce7](https://github.com/GetStream/stream-sdk-java/commit/ae91ce7f1cd3e8bef86eac952503237f8466c6bb))

## [7.2.0](https://github.com/GetStream/stream-sdk-java/compare/7.1.0...7.2.0) (2026-04-30)

## [7.1.0](https://github.com/GetStream/stream-sdk-java/compare/7.0.0...7.1.0) (2026-04-10)


### Features

* allow users to provide a custom OkHttpClient ([780443e](https://github.com/GetStream/stream-sdk-java/commit/780443e0bfb0f658d5f769e43dafa69eba33acad))

## [7.0.0](https://github.com/GetStream/stream-sdk-java/compare/6.1.0...7.0.0) (2026-03-31)


### Features

* regenerate from latest OpenAPI spec, keep only retention runs test ([2c7e910](https://github.com/GetStream/stream-sdk-java/commit/2c7e910f7761a49c9b7199ea5ed862cc4294b9f3))


### Bug Fixes

* apply spotless formatting to generated code ([e593fce](https://github.com/GetStream/stream-sdk-java/commit/e593fce0c9e3965d0db71dd07db4f2ea0f96dfc9))
* remove trailing blank line for spotless ([6b300f7](https://github.com/GetStream/stream-sdk-java/commit/6b300f783f56b0b85c373eef74cd3fecf089313c))
* use lowercase builder method for limit field ([841e0cb](https://github.com/GetStream/stream-sdk-java/commit/841e0cbf125280fd8599d8c970b34e55f63739a5))

## [6.1.0](https://github.com/GetStream/stream-sdk-java/compare/6.0.1...6.1.0) (2026-03-20)

### [6.0.1](https://github.com/GetStream/stream-sdk-java/compare/6.0.0...6.0.1) (2026-03-19)

## [6.0.0](https://github.com/GetStream/stream-sdk-java/compare/5.0.1...6.0.0) (2026-03-05)

### Breaking Changes

- Type names across all products now follow the OpenAPI spec naming convention: response types are suffixed with `Response`, input types with `Request`. See [MIGRATION_v5_to_v6.md](./MIGRATION_v5_to_v6.md) for the complete rename mapping.
- `Event` (WebSocket envelope type) renamed to `WSEvent`. Base event type renamed from `BaseEvent` to `Event` (with field `type` instead of `T`).
- Event composition changed from monolithic `*Preset` embeds to modular `Has*` types.
- `Pager` renamed to `PagerResponse` and migrated from offset-based to cursor-based pagination (`next`/`prev` tokens).

### Added

- Full product coverage: Chat, Video, Moderation, and Feeds APIs are all supported in a single SDK.
- **Feeds**: activities, feeds, feed groups, follows, comments, reactions, collections, bookmarks, membership levels, feed views and more.
- **Video**: calls, recordings, transcription, closed captions, SFU, call statistics, user feedback analytics, and more.
- **Moderation**: flags, review queue, moderation rules, config, appeals, moderation logs, and more.
- Push notification types, preferences, and templates.
- Webhook support: `WHEvent` envelope class for receiving webhook payloads, utility methods for decoding and verifying webhook signatures, and a full set of individual typed event classes for every event across all products (Chat, Video, Moderation, Feeds) usable as discriminated event types.
- Cursor-based pagination across all list endpoints.

### [5.0.1](https://github.com/GetStream/stream-sdk-java/compare/5.0.0...5.0.1) (2026-02-11)

## [5.0.0](https://github.com/GetStream/stream-sdk-java/compare/4.1.1...5.0.0) (2026-02-03)

### [4.1.1](https://github.com/GetStream/stream-sdk-java/compare/4.0.0...4.1.1) (2026-01-06)

## [4.1.0](https://github.com/GetStream/stream-sdk-java/compare/4.0.0...4.1.0) (2026-01-06)

## [4.0.0](https://github.com/GetStream/stream-sdk-java/compare/3.0.3...4.0.0) (2025-09-30)

### [3.0.3-feeds](https://github.com/GetStream/stream-sdk-java/compare/3.0.1...3.0.3-feeds) (2025-08-22)

### [3.0.2-feeds](https://github.com/GetStream/stream-sdk-java/compare/2.0.0...3.0.2-feeds) (2025-08-22)

### [3.0.1-feeds](https://github.com/GetStream/stream-sdk-java/compare/2.0.0...3.0.1-feeds) (2025-08-22)

## [3.0.0-feeds](https://github.com/GetStream/stream-sdk-java/compare/2.0.0...3.0.0-feeds) (2025-08-22)

## [2.0.0](https://github.com/GetStream/stream-sdk-java/compare/1.0.0...2.0.0) (2025-04-30)

## 1.0.0 (2025-02-18)

### 0.1.5 (2025-02-12)

### 0.1.4 (2025-02-12)

### 0.1.3 (2025-02-12)

### 0.1.2 (2025-02-12)

### 0.1.1 (2025-02-12)


## 0.1.0 (2025-02-12)
