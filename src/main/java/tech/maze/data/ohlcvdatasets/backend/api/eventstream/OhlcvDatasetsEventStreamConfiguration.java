package tech.maze.data.ohlcvdatasets.backend.api.eventstream;

import com.google.protobuf.Empty;
import io.cloudevents.CloudEvent;
import java.util.function.Consumer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.maze.commons.eventstream.EventSender;
import tech.maze.commons.eventstream.MazeEventProperties;

/**
 * Event stream configuration for OHLCV datasets processing.
 */
@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EnableConfigurationProperties(MazeEventProperties.class)
@Slf4j
public class OhlcvDatasetsEventStreamConfiguration {
  EventSender eventSender;

  /**
   * Handles OHLCV datasets events delivered via the event stream.
   *
   * @return a consumer for CloudEvents
   */
  @Bean
  public Consumer<CloudEvent> ohlcvDatasetsConsumer() {
    return event -> {
      final String eventType = event.getType();

      if (OhlcvDatasetEventTypes.SYNC_OHLCVS_REQUEST.equals(eventType)) {
        handleSyncRequest(event);
        return;
      }

      if (OhlcvDatasetEventTypes.TRACK_REQUEST.equals(eventType)) {
        handleTrackRequest(event);
        return;
      }

      if (OhlcvDatasetEventTypes.UNTRACK_REQUEST.equals(eventType)) {
        handleUntrackRequest(event);
        return;
      }

      log.warn("Skipping event type {} (no handler registered)", eventType);
    };
  }

  private void handleSyncRequest(CloudEvent event) {
    log.info("Received SyncOHLCVs event with id {}", event.getId());
    sendReplyIfPresent(event, Empty.getDefaultInstance());
  }

  private void handleTrackRequest(CloudEvent event) {
    log.info("Received TrackRequest event with id {}", event.getId());
    sendReplyIfPresent(event, Empty.getDefaultInstance());
  }

  private void handleUntrackRequest(CloudEvent event) {
    log.info("Received UntrackRequest event with id {}", event.getId());
    sendReplyIfPresent(event, Empty.getDefaultInstance());
  }

  private void sendReplyIfPresent(CloudEvent event, com.google.protobuf.Message response) {
    final String replyTo = eventSender.resolveReplyTo(event);
    if (replyTo == null || replyTo.isBlank()) {
      return;
    }

    final boolean sent = eventSender.send(replyTo, response);
    if (!sent) {
      log.error("Failed to dispatch response for event {}", event.getId());
    }
  }

}
