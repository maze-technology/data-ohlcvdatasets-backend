package tech.maze.data.ohlcvdatasets.backend.domain.usecases;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OrderedOhlcvData;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.in.LoadOrderedOhlcvDataUseCase;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.out.LoadOrderedOhlcvDataPort;

/**
 * Use case for loading ordered OHLCV data entries.
 */
@Service
@RequiredArgsConstructor
public class LoadOrderedOhlcvDataService implements LoadOrderedOhlcvDataUseCase {
  private final LoadOrderedOhlcvDataPort loadOrderedOhlcvDataPort;

  @Override
  public List<OrderedOhlcvData> findByDatasetId(UUID datasetId) {
    return loadOrderedOhlcvDataPort.findByDatasetId(datasetId);
  }
}
