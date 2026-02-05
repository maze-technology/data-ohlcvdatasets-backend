package tech.maze.data.ohlcvdatasets.backend.infrastructure.storage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.io.OutputFile;
import org.apache.parquet.io.PositionOutputStream;
import org.springframework.stereotype.Component;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OrderedOhlcvData;

/**
 * Parquet writer for OHLCV datasets.
 */
@Component
@Slf4j
public class ParquetOhlcvDatasetWriter {
  private static final Schema SCHEMA = SchemaBuilder.record("OrderedOhlcvData")
      .namespace("tech.maze.data.ohlcvdatasets.parquet")
      .fields()
      .requiredString("dataset_id")
      .requiredInt("position")
      .name("ohlcv_data")
      .type()
      .bytesType()
      .noDefault()
      .endRecord();

  /**
   * Writes a dataset to a Parquet payload.
   *
   * @param dataset dataset metadata
   * @param orderedData ordered OHLCV data entries
   * @return serialized Parquet payload
   */
  public byte[] write(OhlcvDataset dataset, List<OrderedOhlcvData> orderedData) {
    Objects.requireNonNull(dataset, "dataset must not be null");

    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      final OutputFile outputFile = new InMemoryOutputFile(outputStream);
      final ParquetWriter<GenericRecord> writer = AvroParquetWriter
          .<GenericRecord>builder(outputFile)
          .withSchema(SCHEMA)
          .withExtraMetaData(buildMetadata(dataset))
          .build();
      try (writer) {
        for (OrderedOhlcvData entry : orderedData) {
          writer.write(toRecord(entry));
        }
      }

      return outputStream.toByteArray();
    } catch (IOException ex) {
      log.error("Failed to write Parquet payload for dataset {}", dataset.id(), ex);
      throw new IllegalStateException("Unable to write Parquet payload", ex);
    }
  }

  private Map<String, String> buildMetadata(OhlcvDataset dataset) {
    final Map<String, String> metadata = new HashMap<>();
    metadata.put("dataProvider", dataset.dataProvider());
    metadata.put("timeframeSeconds", Long.toString(dataset.timeframe().getSeconds()));
    metadata.put("startAt", toString(dataset.startAt()));
    metadata.put("endAt", toString(dataset.endAt()));
    metadata.put("orderCount", Integer.toString(dataset.orderCount()));
    metadata.put("marketType", dataset.market().type());
    metadata.put("marketExchange", nullToEmpty(dataset.market().exchange()));
    metadata.put("marketBase", dataset.market().base());
    metadata.put("marketQuote", dataset.market().quote());
    metadata.put("datasetId", toString(dataset.id()));
    return metadata;
  }

  private GenericRecord toRecord(OrderedOhlcvData entry) {
    final GenericRecord record = new GenericData.Record(SCHEMA);
    record.put("dataset_id", toString(entry.datasetId()));
    record.put("position", entry.position());
    record.put("ohlcv_data", entry.data());
    return record;
  }

  private String toString(UUID value) {
    return value == null ? "" : value.toString();
  }

  private String toString(Instant value) {
    return value == null ? "" : value.toString();
  }

  private String nullToEmpty(String value) {
    return value == null ? "" : value;
  }

  private static final class InMemoryOutputFile implements OutputFile {
    private final ByteArrayOutputStream outputStream;

    private InMemoryOutputFile(ByteArrayOutputStream outputStream) {
      this.outputStream = outputStream;
    }

    @Override
    public PositionOutputStream create(long blockSizeHint) {
      return new ByteArrayPositionOutputStream(outputStream);
    }

    @Override
    public PositionOutputStream createOrOverwrite(long blockSizeHint) {
      return new ByteArrayPositionOutputStream(outputStream);
    }

    @Override
    public boolean supportsBlockSize() {
      return false;
    }

    @Override
    public long defaultBlockSize() {
      return 0;
    }
  }

  private static final class ByteArrayPositionOutputStream extends PositionOutputStream {
    private final ByteArrayOutputStream outputStream;
    private long position;

    private ByteArrayPositionOutputStream(ByteArrayOutputStream outputStream) {
      this.outputStream = outputStream;
    }

    @Override
    public long getPos() {
      return position;
    }

    @Override
    public void write(int b) {
      outputStream.write(b);
      position++;
    }

    @Override
    public void write(byte[] b, int off, int len) {
      outputStream.write(b, off, len);
      position += len;
    }
  }
}
