package tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OrderedOhlcvData;
import tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.entities.OrderedOhlcvDataEntity;

/**
 * Maps ordered OHLCV data entities to domain models and back.
 */
@Mapper(componentModel = "spring")
public interface OrderedOhlcvDataEntityMapper {
  /**
   * Map a persistence entity to the domain model.
   *
   * @param entity persistence entity
   * @return domain model
   */
  default OrderedOhlcvData toDomain(OrderedOhlcvDataEntity entity) {
    if (entity == null) {
      return null;
    }
    return new OrderedOhlcvData(
        entity.getOhlcvDatasetId(),
        entity.getPosition(),
        entity.getOhlcvData()
    );
  }

  /**
   * Map a domain model to its persistence entity.
   *
   * @param orderedData domain model
   * @return persistence entity
   */
  default OrderedOhlcvDataEntity toEntity(OrderedOhlcvData orderedData) {
    if (orderedData == null) {
      return null;
    }
    final OrderedOhlcvDataEntity entity = new OrderedOhlcvDataEntity();
    entity.setOhlcvDatasetId(orderedData.datasetId());
    entity.setPosition(orderedData.position());
    entity.setOhlcvData(orderedData.data());
    return entity;
  }
}
