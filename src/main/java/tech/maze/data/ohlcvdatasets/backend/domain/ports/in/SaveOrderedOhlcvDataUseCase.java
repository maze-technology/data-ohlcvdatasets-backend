package tech.maze.data.ohlcvdatasets.backend.domain.ports.in;

import java.util.List;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OrderedOhlcvData;

/**
 * Use case for persisting ordered OHLCV data.
 */
public interface SaveOrderedOhlcvDataUseCase {
  /**
   * Persist ordered OHLCV entries.
   *
   * @param orderedData ordered OHLCV data
   * @return saved entries
   */
  List<OrderedOhlcvData> saveAll(List<OrderedOhlcvData> orderedData);
}
