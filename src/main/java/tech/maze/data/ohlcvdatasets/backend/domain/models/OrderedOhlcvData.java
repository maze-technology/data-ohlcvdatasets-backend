package tech.maze.data.ohlcvdatasets.backend.domain.models;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents an ordered OHLCV data entry.
 *
 * @param datasetId dataset identifier
 * @param position ordered position within the dataset
 * @param data serialized OHLCV payload
 */
public record OrderedOhlcvData(
    UUID datasetId,
    int position,
    byte[] data
) {
  public OrderedOhlcvData {
    Objects.requireNonNull(datasetId, "datasetId must not be null");
    Objects.requireNonNull(data, "data must not be null");
  }
}
