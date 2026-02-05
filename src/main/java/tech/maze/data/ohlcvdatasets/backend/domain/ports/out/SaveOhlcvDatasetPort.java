package tech.maze.data.ohlcvdatasets.backend.domain.ports.out;

import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;

/**
 * Port for saving OHLCV datasets in a persistence store.
 */
public interface SaveOhlcvDatasetPort {
  /**
   * Persist a dataset.
   *
   * @param dataset dataset to persist
   * @return saved dataset
   */
  OhlcvDataset save(OhlcvDataset dataset);
}
