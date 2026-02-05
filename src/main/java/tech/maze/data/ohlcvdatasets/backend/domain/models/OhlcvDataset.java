package tech.maze.data.ohlcvdatasets.backend.domain.models;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Domain model for an OHLCV dataset.
 *
 * @param id dataset identifier
 * @param dataProvider data provider identifier
 * @param timeframe dataset timeframe
 * @param startAt start timestamp for the dataset
 * @param endAt end timestamp for the dataset
 * @param orderCount number of OHLCV entries in the dataset
 * @param createdAt creation timestamp
 * @param market market metadata
 * @param longTermStorageKey key of the stored dataset object (S3/Parquet)
 */
public record OhlcvDataset(
    UUID id,
    String dataProvider,
    Duration timeframe,
    Instant startAt,
    Instant endAt,
    int orderCount,
    Instant createdAt,
    OhlcvDatasetMarket market,
    String longTermStorageKey
) {
  /**
   * Creates an OHLCV dataset instance.
   *
   * @param id dataset identifier
   * @param dataProvider data provider identifier
   * @param timeframe dataset timeframe
   * @param startAt start timestamp
   * @param endAt end timestamp
   * @param orderCount number of entries
   * @param createdAt creation timestamp
   * @param market market metadata
   * @param longTermStorageKey storage key (S3/Parquet)
   */
  public OhlcvDataset {
    Objects.requireNonNull(dataProvider, "dataProvider must not be null");
    Objects.requireNonNull(timeframe, "timeframe must not be null");
    Objects.requireNonNull(startAt, "startAt must not be null");
    Objects.requireNonNull(endAt, "endAt must not be null");
    Objects.requireNonNull(market, "market must not be null");
  }
}
