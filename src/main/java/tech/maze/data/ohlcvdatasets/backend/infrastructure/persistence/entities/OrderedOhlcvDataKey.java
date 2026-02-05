package tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Composite key for ordered OHLCV data entities.
 */
public class OrderedOhlcvDataKey implements Serializable {
  private UUID ohlcvDatasetId;
  private int position;

  public OrderedOhlcvDataKey() {
  }

  public OrderedOhlcvDataKey(UUID ohlcvDatasetId, int position) {
    this.ohlcvDatasetId = ohlcvDatasetId;
    this.position = position;
  }

  public UUID getOhlcvDatasetId() {
    return ohlcvDatasetId;
  }

  public int getPosition() {
    return position;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    final OrderedOhlcvDataKey that = (OrderedOhlcvDataKey) other;
    return position == that.position
        && Objects.equals(ohlcvDatasetId, that.ohlcvDatasetId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ohlcvDatasetId, position);
  }
}
