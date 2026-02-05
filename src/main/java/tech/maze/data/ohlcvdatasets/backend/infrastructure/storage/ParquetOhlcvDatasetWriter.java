package tech.maze.data.ohlcvdatasets.backend.infrastructure.storage;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OrderedOhlcvData;

/**
 * Parquet writer for OHLCV datasets.
 *
 * <p>Implementation stub: returns a placeholder payload until a vetted Parquet stack is approved.
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
    Objects.requireNonNull(dataset, "dataset must not be null");

    log.warn("Parquet writer is a stub. Returning placeholder payload.");
    final String payload = String.format(
        "{\"datasetId\":\"%s\",\"entries\":%d,\"parquetStub\":true}",
        dataset.id(),
        orderedData.size()
    );
    return payload.getBytes(StandardCharsets.UTF_8);
  }
}
