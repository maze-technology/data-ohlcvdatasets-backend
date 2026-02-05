package tech.maze.data.ohlcvdatasets.backend.domain.ports.out;

import java.util.List;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OrderedOhlcvData;

/**
 * Port for saving ordered OHLCV data entries.
 */
public interface SaveOrderedOhlcvDataPort {
  /**
   * Persist ordered OHLCV entries.
   *
   * @param orderedData ordered OHLCV entries
   * @return saved entries
   */
  List<OrderedOhlcvData> saveAll(List<OrderedOhlcvData> orderedData);
}
