package tech.maze.data.ohlcvdatasets.backend.infrastructure.storage;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OrderedOhlcvData;

/**
 * Parquet writer for OHLCV datasets.
 *
 * <p>This is a stub implementation that will be replaced by a proper Parquet writer.
 */
@Component
@Slf4j
public class ParquetOhlcvDatasetWriter {
  /**
   * Writes a dataset to a Parquet payload.
   *
   * @param dataset dataset metadata
   * @param orderedData ordered OHLCV data entries
   * @return serialized Parquet payload
   */
  public byte[] write(OhlcvDataset dataset, List<OrderedOhlcvData> orderedData) {
    log.warn("Parquet writer is not implemented yet. Returning an empty payload.");
    return new byte[0];
  }
}
