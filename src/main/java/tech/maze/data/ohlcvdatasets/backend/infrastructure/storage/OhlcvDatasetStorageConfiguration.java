package tech.maze.data.ohlcvdatasets.backend.infrastructure.storage;

import java.net.URI;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;

/**
 * Configuration for OHLCV dataset storage.
 */
@Configuration
@EnableConfigurationProperties(OhlcvDatasetStorageProperties.class)
public class OhlcvDatasetStorageConfiguration {
  /**
   * Builds the S3 client for dataset storage.
   *
   * @param properties storage properties
   * @return configured S3 client
   */
  @Bean
  @ConditionalOnProperty(name = "maze.storage.ohlcvdatasets.enabled", havingValue = "true")
  public S3Client ohlcvDatasetsS3Client(OhlcvDatasetStorageProperties properties) {
    final S3ClientBuilder builder = S3Client.builder()
        .region(Region.of(properties.getRegion()))
        .credentialsProvider(DefaultCredentialsProvider.create());

    if (properties.getEndpoint() != null && !properties.getEndpoint().isBlank()) {
      builder.endpointOverride(URI.create(properties.getEndpoint()));
    }

    if (properties.isForcePathStyle()) {
      builder.forcePathStyle(true);
    }

    return builder.build();
  }
}
