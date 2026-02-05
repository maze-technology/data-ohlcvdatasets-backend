package tech.maze.data.ohlcvdatasets.backend.domain.usecases;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.in.SearchOhlcvDatasetsUseCase;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.out.SearchOhlcvDatasetsPort;

/**
 * Use case for listing OHLCV datasets.
 */
@Service
@RequiredArgsConstructor
public class SearchOhlcvDatasetsService implements SearchOhlcvDatasetsUseCase {
  private final SearchOhlcvDatasetsPort searchOhlcvDatasetsPort;

  @Override
  public List<OhlcvDataset> findAll() {
    return searchOhlcvDatasetsPort.findAll();
  }
}
