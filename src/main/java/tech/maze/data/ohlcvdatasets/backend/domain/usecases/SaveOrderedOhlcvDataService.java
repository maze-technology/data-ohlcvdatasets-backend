package tech.maze.data.ohlcvdatasets.backend.domain.usecases;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OrderedOhlcvData;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.in.SaveOrderedOhlcvDataUseCase;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.out.SaveOrderedOhlcvDataPort;

/**
 * Use case for saving ordered OHLCV data entries.
 */
@Service
@RequiredArgsConstructor
public class SaveOrderedOhlcvDataService implements SaveOrderedOhlcvDataUseCase {
  private final SaveOrderedOhlcvDataPort saveOrderedOhlcvDataPort;

  @Override
  public List<OrderedOhlcvData> saveAll(List<OrderedOhlcvData> orderedData) {
    return saveOrderedOhlcvDataPort.saveAll(orderedData);
  }
}
