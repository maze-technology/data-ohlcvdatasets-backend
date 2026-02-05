package tech.maze.data.ohlcvdatasets.backend.domain.ports.in;

import java.util.Optional;
import java.util.UUID;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;

/**
 * Use case for retrieving an OHLCV dataset by id.
 */
public interface FindOhlcvDatasetUseCase {
  /**
   * Find an OHLCV dataset by its identifier.
   *
   * @param id dataset identifier
   * @return optional dataset
   */
  Optional<OhlcvDataset> findById(UUID id);
}
