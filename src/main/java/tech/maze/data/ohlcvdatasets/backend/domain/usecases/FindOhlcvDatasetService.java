package tech.maze.data.ohlcvdatasets.backend.domain.usecases;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.in.FindOhlcvDatasetUseCase;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.out.LoadOhlcvDatasetPort;

/**
 * Use case for loading an OHLCV dataset by id.
 */
@Service
@RequiredArgsConstructor
public class FindOhlcvDatasetService implements FindOhlcvDatasetUseCase {
  private final LoadOhlcvDatasetPort loadOhlcvDatasetPort;

  @Override
  public Optional<OhlcvDataset> findById(UUID id) {
    return loadOhlcvDatasetPort.findById(id);
  }
}
