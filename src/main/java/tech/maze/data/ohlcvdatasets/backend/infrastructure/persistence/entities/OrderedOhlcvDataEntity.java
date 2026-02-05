package tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA entity for ordered OHLCV data entries.
 */
@Entity
@Table(name = "ordered_ohlcvs_data")
@IdClass(OrderedOhlcvDataKey.class)
@Getter
@Setter
@NoArgsConstructor
public class OrderedOhlcvDataEntity {
  @Id
  @Column(name = "ohlcv_dataset_id", nullable = false)
  private UUID ohlcvDatasetId;

  @Id
  @Column(name = "position", nullable = false)
  private int position;

  @Column(name = "ohlcv_data", nullable = false)
  private byte[] ohlcvData;
}
