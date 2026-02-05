package tech.maze.data.ohlcvdatasets.backend.api.eventstream;

/**
 * Temporary event type constants for OHLCV datasets.
 *
 * <p>TODO: Replace with smithy-event-codegen generated constants.
 */
public final class OhlcvDatasetEventTypes {
  public static final String SYNC_OHLCVS_REQUEST = "tech.maze.ohlcvdatasets.sync.request";
  public static final String SYNC_OHLCVS_RESPONSE = "tech.maze.ohlcvdatasets.sync.response";
  public static final String TRACK_REQUEST = "tech.maze.ohlcvdatasets.track.request";
  public static final String TRACK_RESPONSE = "tech.maze.ohlcvdatasets.track.response";
  public static final String UNTRACK_REQUEST = "tech.maze.ohlcvdatasets.untrack.request";
  public static final String UNTRACK_RESPONSE = "tech.maze.ohlcvdatasets.untrack.response";

  private OhlcvDatasetEventTypes() {
  }
}
