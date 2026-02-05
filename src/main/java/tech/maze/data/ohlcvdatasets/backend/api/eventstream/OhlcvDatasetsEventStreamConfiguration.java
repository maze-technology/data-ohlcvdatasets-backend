package tech.maze.data.ohlcvdatasets.backend.api.eventstream;

import com.google.protobuf.InvalidProtocolBufferException;
import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;
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
import tech.maze.dtos.ohlcvdatasets.monitoredmarkets.requests.TrackRequest;
import tech.maze.dtos.ohlcvdatasets.monitoredmarkets.requests.TrackResponse;
import tech.maze.dtos.ohlcvdatasets.monitoredmarkets.requests.UntrackRequest;
import tech.maze.dtos.ohlcvdatasets.monitoredmarkets.requests.UntrackResponse;
import tech.maze.dtos.ohlcvdatasets.requests.SyncOHLCVs;
import tech.maze.dtos.ohlcvdatasets.requests.SyncOHLCVsResponse;

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
    final SyncOHLCVs request = parse(event, SyncOHLCVs.parser());
    log.info("Received SyncOHLCVsRequest: {}", request);

    final SyncOHLCVsResponse response = SyncOHLCVsResponse.newBuilder().build();
    sendReplyIfPresent(event, response);
  }

  private void handleTrackRequest(CloudEvent event) {
    final TrackRequest request = parse(event, TrackRequest.parser());
    log.info("Received TrackRequest: {}", request);

    final TrackResponse response = TrackResponse.newBuilder().build();
    sendReplyIfPresent(event, response);
  }

  private void handleUntrackRequest(CloudEvent event) {
    final UntrackRequest request = parse(event, UntrackRequest.parser());
    log.info("Received UntrackRequest: {}", request);

    final UntrackResponse response = UntrackResponse.newBuilder().build();
    sendReplyIfPresent(event, response);
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

  private <T extends com.google.protobuf.Message> T parse(
      CloudEvent event,
      com.google.protobuf.Parser<T> parser
  ) {
    final byte[] bytes = extractBytes(event);
    try {
      return parser.parseFrom(bytes);
    } catch (InvalidProtocolBufferException ex) {
      throw new IllegalArgumentException("Failed to decode event payload", ex);
    }
  }

  private byte[] extractBytes(CloudEvent event) {
    final CloudEventData data = event.getData();
    if (data == null) {
      throw new IllegalArgumentException("CloudEvent has no data");
    }

    return data.toBytes();
  }
}
