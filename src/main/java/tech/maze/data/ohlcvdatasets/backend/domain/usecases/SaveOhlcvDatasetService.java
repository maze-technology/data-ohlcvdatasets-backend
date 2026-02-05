package tech.maze.data.ohlcvdatasets.backend.domain.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.in.SaveOhlcvDatasetUseCase;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.out.SaveOhlcvDatasetPort;

/**
 * Use case for saving OHLCV datasets.
 */
@Service
@RequiredArgsConstructor
public class SaveOhlcvDatasetService implements SaveOhlcvDatasetUseCase {
  private final SaveOhlcvDatasetPort saveOhlcvDatasetPort;

  @Override
  public OhlcvDataset save(OhlcvDataset dataset) {
    return saveOhlcvDatasetPort.save(dataset);
  }
}
