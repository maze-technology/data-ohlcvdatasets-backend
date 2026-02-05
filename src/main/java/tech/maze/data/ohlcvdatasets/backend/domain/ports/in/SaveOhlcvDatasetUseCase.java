package tech.maze.data.ohlcvdatasets.backend.domain.ports.in;

import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;

/**
 * Use case for saving OHLCV datasets.
 */
public interface SaveOhlcvDatasetUseCase {
  /**
   * Persist an OHLCV dataset.
   *
   * @param dataset dataset to persist
   * @return saved dataset
   */
  OhlcvDataset save(OhlcvDataset dataset);
}
