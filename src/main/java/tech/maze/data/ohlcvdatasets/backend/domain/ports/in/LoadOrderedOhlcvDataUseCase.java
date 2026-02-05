package tech.maze.data.ohlcvdatasets.backend.domain.ports.in;

import java.util.List;
import java.util.UUID;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OrderedOhlcvData;

/**
 * Use case for loading ordered OHLCV data by dataset.
 */
public interface LoadOrderedOhlcvDataUseCase {
  /**
   * Load ordered OHLCV entries for a dataset.
   *
   * @param datasetId dataset identifier
   * @return ordered OHLCV entries
   */
  List<OrderedOhlcvData> findByDatasetId(UUID datasetId);
}
