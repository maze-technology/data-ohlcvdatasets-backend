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

  /**
   * Default constructor for JPA.
   */
  public OrderedOhlcvDataKey() {
  }

  /**
   * Creates a composite key for ordered OHLCV data.
   *
   * @param ohlcvDatasetId dataset identifier
   * @param position ordered position
   */
  public OrderedOhlcvDataKey(UUID ohlcvDatasetId, int position) {
    this.ohlcvDatasetId = ohlcvDatasetId;
    this.position = position;
  }

  /**
   * Returns the dataset identifier.
   *
   * @return dataset identifier
   */
  public UUID getOhlcvDatasetId() {
    return ohlcvDatasetId;
  }

  /**
   * Returns the ordered position.
   *
   * @return ordered position
   */
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
