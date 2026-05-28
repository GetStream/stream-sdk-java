package io.getstream.exceptions;

import org.jetbrains.annotations.Nullable;

/**
 * Thrown when an async task observed via {@code waitForTask} reaches {@code status: "failed"}.
 * Populated from the task's {@code ErrorResult}. Per CHA-2958 §5.4.
 */
public class StreamTaskException extends StreamException {
  private static final long serialVersionUID = 1L;

  private final String taskId;
  private final String errorType;
  private final String description;
  @Nullable private final String stackTraceText;
  @Nullable private final String version;

  public StreamTaskException(
      String taskId,
      String errorType,
      String description,
      @Nullable String stackTraceText,
      @Nullable String version) {
    super(buildMessage(taskId, errorType, description), (Throwable) null);
    this.taskId = taskId;
    this.errorType = errorType;
    this.description = description;
    this.stackTraceText = stackTraceText;
    this.version = version;
  }

  private static String buildMessage(String taskId, String errorType, String description) {
    return String.format(
        "task %s failed: %s%s", taskId, errorType, description != null ? " — " + description : "");
  }

  public String getTaskId() {
    return taskId;
  }

  public String getErrorType() {
    return errorType;
  }

  public String getDescription() {
    return description;
  }

  /**
   * Returns the server-side stack trace from {@code ErrorResult.stacktrace}, or {@code null}.
   * Named {@code getStackTraceText} so it does not collide with
   * {@link Throwable#getStackTrace()}.
   */
  @Nullable
  public String getStackTraceText() {
    return stackTraceText;
  }

  @Nullable
  public String getVersion() {
    return version;
  }
}
