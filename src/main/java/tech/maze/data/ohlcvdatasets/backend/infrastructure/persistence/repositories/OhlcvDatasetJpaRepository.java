package tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.entities.OhlcvDatasetEntity;

/**
 * JPA repository for OHLCV datasets.
 */
@Repository
public interface OhlcvDatasetJpaRepository extends JpaRepository<OhlcvDatasetEntity, UUID> {}
