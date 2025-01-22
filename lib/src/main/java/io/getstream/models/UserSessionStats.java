package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserSessionStats {

  @JsonProperty("freeze_duration_seconds")
  private Integer freezeDurationSeconds;

  @JsonProperty("group")
  private String group;

  @JsonProperty("max_freeze_fraction")
  private Double maxFreezeFraction;

  @JsonProperty("max_freezes_duration_seconds")
  private Integer maxFreezesDurationSeconds;

  @JsonProperty("min_event_ts")
  private Integer minEventTs;

  @JsonProperty("packet_loss_fraction")
  private Double packetLossFraction;

  @JsonProperty("publisher_packet_loss_fraction")
  private Double publisherPacketLossFraction;

  @JsonProperty("publishing_duration_seconds")
  private Integer publishingDurationSeconds;

  @JsonProperty("quality_score")
  private Double qualityScore;

  @JsonProperty("receiving_duration_seconds")
  private Integer receivingDurationSeconds;

  @JsonProperty("session_id")
  private String sessionID;

  @JsonProperty("total_pixels_in")
  private Integer totalPixelsIn;

  @JsonProperty("total_pixels_out")
  private Integer totalPixelsOut;

  @Nullable
  @JsonProperty("average_connection_time")
  private Double averageConnectionTime;

  @Nullable
  @JsonProperty("browser")
  private String browser;

  @Nullable
  @JsonProperty("browser_version")
  private String browserVersion;

  @Nullable
  @JsonProperty("current_ip")
  private String currentIp;

  @Nullable
  @JsonProperty("current_sfu")
  private String currentSfu;

  @Nullable
  @JsonProperty("device_model")
  private String deviceModel;

  @Nullable
  @JsonProperty("device_version")
  private String deviceVersion;

  @Nullable
  @JsonProperty("distance_to_sfu_kilometers")
  private Double distanceToSfuKilometers;

  @Nullable
  @JsonProperty("max_fir_per_second")
  private Double maxFirPerSecond;

  @Nullable
  @JsonProperty("max_freezes_per_second")
  private Double maxFreezesPerSecond;

  @Nullable
  @JsonProperty("max_nack_per_second")
  private Double maxNackPerSecond;

  @Nullable
  @JsonProperty("max_pli_per_second")
  private Double maxPliPerSecond;

  @Nullable
  @JsonProperty("os")
  private String os;

  @Nullable
  @JsonProperty("os_version")
  private String osVersion;

  @Nullable
  @JsonProperty("publisher_noise_cancellation_seconds")
  private Double publisherNoiseCancellationSeconds;

  @Nullable
  @JsonProperty("publisher_quality_limitation_fraction")
  private Double publisherQualityLimitationFraction;

  @Nullable
  @JsonProperty("publishing_audio_codec")
  private String publishingAudioCodec;

  @Nullable
  @JsonProperty("publishing_video_codec")
  private String publishingVideoCodec;

  @Nullable
  @JsonProperty("receiving_audio_codec")
  private String receivingAudioCodec;

  @Nullable
  @JsonProperty("receiving_video_codec")
  private String receivingVideoCodec;

  @Nullable
  @JsonProperty("sdk")
  private String sdk;

  @Nullable
  @JsonProperty("sdk_version")
  private String sdkVersion;

  @Nullable
  @JsonProperty("subscriber_video_quality_throttled_duration_seconds")
  private Double subscriberVideoQualityThrottledDurationSeconds;

  @Nullable
  @JsonProperty("truncated")
  private Boolean truncated;

  @Nullable
  @JsonProperty("webrtc_version")
  private String webrtcVersion;

  @Nullable
  @JsonProperty("published_tracks")
  private List<PublishedTrackInfo> publishedTracks;

  @Nullable
  @JsonProperty("subsessions")
  private List<Subsession> subsessions;

  @Nullable
  @JsonProperty("geolocation")
  private GeolocationResult geolocation;

  @Nullable
  @JsonProperty("jitter")
  private TimeStats jitter;

  @Nullable
  @JsonProperty("latency")
  private TimeStats latency;

  @Nullable
  @JsonProperty("max_publishing_video_quality")
  private VideoQuality maxPublishingVideoQuality;

  @Nullable
  @JsonProperty("max_receiving_video_quality")
  private VideoQuality maxReceivingVideoQuality;

  @Nullable
  @JsonProperty("pub_sub_hints")
  private MediaPubSubHint pubSubHints;

  @Nullable
  @JsonProperty("publisher_jitter")
  private TimeStats publisherJitter;

  @Nullable
  @JsonProperty("publisher_latency")
  private TimeStats publisherLatency;

  @Nullable
  @JsonProperty("publisher_video_quality_limitation_duration_seconds")
  private Map<String, Double> publisherVideoQualityLimitationDurationSeconds;

  @Nullable
  @JsonProperty("subscriber_jitter")
  private TimeStats subscriberJitter;

  @Nullable
  @JsonProperty("subscriber_latency")
  private TimeStats subscriberLatency;

  @Nullable
  @JsonProperty("timeline")
  private CallTimeline timeline;
}
