# Activity Metrics

Track engagement on activities — views, clicks, impressions, and more. Metrics are automatically aggregated and stored on each activity, and can be used to rank feeds by popularity.

## What It Does

- **Track events** like views, clicks, and impressions on any activity
- **Automatic rate limiting** — each user is limited per activity per metric (e.g., one user can't inflate a view count by refreshing)
- **Batch tracking** — send up to 100 events in a single API call
- **Metrics on activity** — tracked metrics appear as a `metrics` map on the activity object (e.g., `{"views": 142, "clicks": 38}`)
- **Ranking support** — use metrics in feed-view ranking expressions to surface popular content

## Default Metrics

Every paid-plan app gets three built-in metrics out of the box:

| Metric | Rate Limit (per user, per activity, per hour) |
|--------|-----------------------------------------------|
| `views` | 10 |
| `clicks` | 5 |
| `impressions` | 50 |

No configuration needed — these work immediately.

## How to Use It (Java SDK)

### 1. Track a Single Event

```java
// Track a "view" for an activity
TrackActivityMetricsRequest request = TrackActivityMetricsRequest.builder()
    .userID("user-123")
    .events(List.of(
        TrackActivityMetricsEvent.builder()
            .activityID("activity-abc")
            .metric("views")
            .build()
    ))
    .build();

TrackActivityMetricsResponse response = feeds.trackActivityMetrics(request)
    .execute()
    .getData();

// Check if the event was accepted
TrackActivityMetricsEventResult result = response.getResults().get(0);
if (result.getAllowed()) {
    System.out.println("View tracked successfully");
} else {
    System.out.println("Rate limited — user has already viewed this activity too many times");
}
```

### 2. Track Multiple Events in a Batch

```java
// Track different metrics across multiple activities in one call
TrackActivityMetricsRequest request = TrackActivityMetricsRequest.builder()
    .userID("user-123")
    .events(List.of(
        TrackActivityMetricsEvent.builder()
            .activityID("activity-abc")
            .metric("views")
            .build(),
        TrackActivityMetricsEvent.builder()
            .activityID("activity-abc")
            .metric("clicks")
            .build(),
        TrackActivityMetricsEvent.builder()
            .activityID("activity-xyz")
            .metric("impressions")
            .delta(5)  // count 5 impressions at once
            .build()
    ))
    .build();

TrackActivityMetricsResponse response = feeds.trackActivityMetrics(request)
    .execute()
    .getData();

// Each event has its own result — some may be allowed, others rate-limited
for (TrackActivityMetricsEventResult result : response.getResults()) {
    System.out.println(result.getActivityID() + " " + result.getMetric()
        + " → " + (result.getAllowed() ? "tracked" : "rate limited"));
}
```

### 3. Read Metrics from an Activity

Metrics are available on the activity object after they've been synced (within ~5 minutes of tracking).

```java
GetActivityResponse response = feeds.getActivity("activity-abc").execute().getData();

Map<String, Integer> metrics = response.getActivity().getMetrics();
// e.g. {"views": 142, "clicks": 38, "impressions": 920}

if (metrics != null) {
    System.out.println("Views: " + metrics.getOrDefault("views", 0));
    System.out.println("Clicks: " + metrics.getOrDefault("clicks", 0));
}
```

### 4. Use Metrics for Feed Ranking

You can reference metrics in feed-view ranking expressions to rank activities by engagement. For example, configure a feed view with a ranking score like:

```
metrics.views * 1.0 + metrics.clicks * 3.0
```

This surfaces content with more clicks and views higher in the feed.

## Request / Response Reference

### TrackActivityMetricsRequest

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| `events` | `List<TrackActivityMetricsEvent>` | Yes | 1–100 metric events to track |
| `userID` | `String` | Server-side | Required for server-side API calls |

### TrackActivityMetricsEvent

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| `activityID` | `String` | Yes | The activity to track the metric for |
| `metric` | `String` | Yes | Metric name (e.g., `views`, `clicks`, `impressions`) |
| `delta` | `Integer` | No | Amount to increment (default: `1`) |

### TrackActivityMetricsEventResult

| Field | Type | Description |
|-------|------|-------------|
| `activityID` | `String` | The activity ID |
| `metric` | `String` | The metric name |
| `allowed` | `Boolean` | `true` if tracked, `false` if rate-limited |
| `error` | `String` | Error message if the event was rejected (e.g., unknown metric name) |

## Rate Limiting

Each user is independently rate-limited per activity per metric within a 1-hour sliding window. If a user exceeds the limit, the event is **not** counted and the response returns `allowed: false`. This is not an error — your app can silently ignore it or show a message.

## Good to Know

- **Sync delay** — Metrics appear on the activity object within ~5 minutes of tracking. They are not instantly reflected.
- **Metrics in queries** — Metrics can be used in ranking expressions but cannot be used as query filters (e.g., you can't filter for `metrics.views > 100`).

---

## Coming Soon: Configurable Metrics (App-Level)

> *Expected next week — pending backend release.*

Currently, all apps use the same three default metrics (`views`, `clicks`, `impressions`) with fixed rate limits. The upcoming release adds the ability to **configure metrics at the app level**:

- **Define custom metric names** — track domain-specific signals like `shares`, `saves`, `watch_time`, `add_to_cart`, etc.
- **Set per-metric rate limits** — control the max events per user per activity per hour for each metric
- **Disable default metrics** — set a default metric's limit to `0` to turn it off
- **Up to 10 custom metrics** on Enterprise plans; self-service plans continue with the 3 defaults
- Configured via the **Update App Settings** API — no SDK update or code change required to add or modify metrics
