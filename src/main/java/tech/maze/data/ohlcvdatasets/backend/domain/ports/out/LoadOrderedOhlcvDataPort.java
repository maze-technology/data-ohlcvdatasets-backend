package tech.maze.data.ohlcvdatasets.backend.domain.ports.out;

import java.util.List;
import java.util.UUID;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OrderedOhlcvData;

/**
 * Port for loading ordered OHLCV data entries.
 */
public interface LoadOrderedOhlcvDataPort {
  /**
   * Load ordered OHLCV entries for a dataset.
   *
   * @param datasetId dataset identifier
   * @return ordered OHLCV entries
   */
  List<OrderedOhlcvData> findByDatasetId(UUID datasetId);
}
