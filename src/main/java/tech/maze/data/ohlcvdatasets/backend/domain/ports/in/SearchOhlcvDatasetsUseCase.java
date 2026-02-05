package tech.maze.data.ohlcvdatasets.backend.domain.ports.in;

import java.util.List;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;

/**
 * Use case for listing OHLCV datasets.
 */
public interface SearchOhlcvDatasetsUseCase {
  /**
   * Fetch all datasets.
   *
   * @return list of datasets
   */
  List<OhlcvDataset> findAll();
}
