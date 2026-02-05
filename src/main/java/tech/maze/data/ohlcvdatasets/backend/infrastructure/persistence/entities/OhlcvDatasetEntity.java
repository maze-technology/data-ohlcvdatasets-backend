package tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

/**
 * JPA entity for OHLCV datasets.
 */
@Entity
@Table(name = "ohlcv_datasets")
@Getter
@Setter
@NoArgsConstructor
public class OhlcvDatasetEntity {
  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID id;

  @Column(name = "market_type", nullable = false, length = 255)
  private String marketType;

  @Column(name = "exchange", length = 255)
  private String exchange;

  @Column(name = "base", nullable = false, length = 255)
  private String base;

  @Column(name = "quote", nullable = false, length = 255)
  private String quote;

  @Column(name = "data_provider", nullable = false, length = 255)
  private String dataProvider;

  @Column(name = "timeframe_seconds", nullable = false)
  private long timeframeSeconds;

  @Column(name = "start_at", nullable = false)
  private Instant startAt;

  @Column(name = "end_at", nullable = false)
  private Instant endAt;

  @Column(name = "order_count", nullable = false)
  private int orderCount;

  @Column(name = "long_term_storage_key", length = 255)
  private String longTermStorageKey;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;
}
