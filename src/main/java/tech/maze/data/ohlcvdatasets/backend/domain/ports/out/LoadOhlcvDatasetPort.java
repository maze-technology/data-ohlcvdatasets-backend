package tech.maze.data.ohlcvdatasets.backend.domain.ports.out;

import java.util.Optional;
import java.util.UUID;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;

/**
 * Port for loading OHLCV datasets from a persistence store.
 */
public interface LoadOhlcvDatasetPort {
  /**
   * Load a dataset by id.
   *
   * @param id dataset identifier
   * @return optional dataset
   */
  Optional<OhlcvDataset> findById(UUID id);
}
