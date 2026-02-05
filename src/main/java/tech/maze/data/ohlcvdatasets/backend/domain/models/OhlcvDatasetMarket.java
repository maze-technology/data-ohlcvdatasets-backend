package tech.maze.data.ohlcvdatasets.backend.domain.models;

import java.util.Objects;

/**
 * Market information attached to an OHLCV dataset.
 *
 * @param type market type (spot, perp, option, etc.)
 * @param exchange exchange identifier
 * @param base base asset identifier
 * @param quote quote asset identifier
 */
public record OhlcvDatasetMarket(
    String type,
    String exchange,
    String base,
    String quote
) {
  /**
   * Creates a market descriptor for an OHLCV dataset.
   *
   * @param type market type
   * @param exchange exchange identifier
   * @param base base asset identifier
   * @param quote quote asset identifier
   */
  public OhlcvDatasetMarket {
    Objects.requireNonNull(type, "type must not be null");
    Objects.requireNonNull(base, "base must not be null");
    Objects.requireNonNull(quote, "quote must not be null");
  }
}
