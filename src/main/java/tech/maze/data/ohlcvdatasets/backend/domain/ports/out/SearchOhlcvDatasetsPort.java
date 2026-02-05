package tech.maze.data.ohlcvdatasets.backend.domain.ports.out;

import java.util.List;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;

/**
 * Port for listing OHLCV datasets from a persistence store.
 */
public interface SearchOhlcvDatasetsPort {
  /**
   * Load all datasets.
   *
   * @return list of datasets
   */
  List<OhlcvDataset> findAll();
}
