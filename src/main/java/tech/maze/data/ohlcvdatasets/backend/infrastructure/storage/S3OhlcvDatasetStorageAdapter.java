package tech.maze.data.ohlcvdatasets.backend.infrastructure.storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OrderedOhlcvData;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.out.OhlcvDatasetStoragePort;

/**
 * S3-backed storage adapter for OHLCV datasets.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class S3OhlcvDatasetStorageAdapter implements OhlcvDatasetStoragePort {
  private final S3Client s3Client;
  private final OhlcvDatasetStorageProperties properties;
  private final ParquetOhlcvDatasetWriter parquetWriter;

  @Override
  public String storeDataset(OhlcvDataset dataset, List<OrderedOhlcvData> orderedData) {
    final String storageKey = buildStorageKey(dataset.id());
    final byte[] payload = parquetWriter.write(dataset, orderedData);

    final PutObjectRequest request = PutObjectRequest.builder()
        .bucket(properties.getBucket())
        .key(storageKey)
        .contentType("application/x-parquet")
        .build();

    s3Client.putObject(request, RequestBody.fromBytes(payload));
    return storageKey;
  }

  @Override
  public Optional<byte[]> loadDataset(String storageKey) {
    final GetObjectRequest request = GetObjectRequest.builder()
        .bucket(properties.getBucket())
        .key(storageKey)
        .build();

    try (ResponseInputStream<GetObjectResponse> response = s3Client.getObject(request)) {
      return Optional.of(readAllBytes(response));
    } catch (NoSuchKeyException ex) {
      log.warn("No dataset found in S3 for key {}", storageKey);
      return Optional.empty();
    } catch (S3Exception | IOException ex) {
      log.error("Failed to read dataset from S3 for key {}", storageKey, ex);
      return Optional.empty();
    }
  }

  private String buildStorageKey(UUID datasetId) {
    final String prefix = properties.getPrefix();
    final String base = prefix == null || prefix.isBlank() ? "ohlcv-datasets" : prefix;
    final String id = datasetId == null ? UUID.randomUUID().toString() : datasetId.toString();
    return base + "/" + id + ".parquet";
  }

  private byte[] readAllBytes(InputStream inputStream) throws IOException {
    return inputStream.readAllBytes();
  }
}
