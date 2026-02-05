package tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.entities.OrderedOhlcvDataEntity;
import tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.entities.OrderedOhlcvDataKey;

/**
 * JPA repository for ordered OHLCV data entries.
 */
@Repository
public interface OrderedOhlcvDataJpaRepository
    extends JpaRepository<OrderedOhlcvDataEntity, OrderedOhlcvDataKey> {
  /**
   * Find ordered OHLCV data for a dataset.
   *
   * @param ohlcvDatasetId dataset identifier
   * @return ordered OHLCV data
   */
  List<OrderedOhlcvDataEntity> findByOhlcvDatasetIdOrderByPositionAsc(UUID ohlcvDatasetId);
}
