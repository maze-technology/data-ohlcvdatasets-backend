package tech.maze.data.ohlcvdatasets.backend.infrastructure.storage;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for OHLCV dataset storage.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = OhlcvDatasetStorageProperties.PREFIX)
public class OhlcvDatasetStorageProperties {
  public static final String PREFIX = "maze.storage.ohlcvdatasets";

  boolean enabled;
  String bucket;
  String prefix = "ohlcv-datasets";
  String region = "eu-central-1";
  String endpoint;
  boolean forcePathStyle;
}
