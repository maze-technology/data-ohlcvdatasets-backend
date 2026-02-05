package tech.maze.data.ohlcvdatasets.backend.domain.ports.out;

import java.util.List;
import java.util.Optional;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OrderedOhlcvData;

/**
 * Port for storing OHLCV datasets in long-term storage.
 */
public interface OhlcvDatasetStoragePort {
  /**
   * Store an OHLCV dataset and its ordered data in long-term storage.
   *
   * @param dataset dataset metadata
   * @param orderedData ordered OHLCV data entries
   * @return storage key for the persisted dataset
   */
  String storeDataset(OhlcvDataset dataset, List<OrderedOhlcvData> orderedData);

  /**
   * Load a stored dataset from long-term storage.
   *
   * @param storageKey storage key
   * @return optional serialized payload
   */
  Optional<byte[]> loadDataset(String storageKey);
}
