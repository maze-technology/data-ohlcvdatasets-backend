package tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.mappers;

import java.time.Duration;
import org.mapstruct.Mapper;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDatasetMarket;
import tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.entities.OhlcvDatasetEntity;

/**
 * Maps OHLCV dataset entities to domain models and back.
 */
@Mapper(componentModel = "spring")
public interface OhlcvDatasetEntityMapper {
  /**
   * Map a persistence entity to the domain model.
   *
   * @param entity persistence entity
   * @return domain model
   */
  default OhlcvDataset toDomain(OhlcvDatasetEntity entity) {
    if (entity == null) {
      return null;
    }
    final OhlcvDatasetMarket market = new OhlcvDatasetMarket(
        entity.getMarketType(),
        entity.getExchange(),
        entity.getBase(),
        entity.getQuote()
    );
    return new OhlcvDataset(
        entity.getId(),
        entity.getDataProvider(),
        Duration.ofSeconds(entity.getTimeframeSeconds()),
        entity.getStartAt(),
        entity.getEndAt(),
        entity.getOrderCount(),
        entity.getCreatedAt(),
        market,
        entity.getLongTermStorageKey()
    );
  }

  /**
   * Map a domain model to its persistence entity.
   *
   * @param dataset domain model
   * @return persistence entity
   */
  default OhlcvDatasetEntity toEntity(OhlcvDataset dataset) {
    if (dataset == null) {
      return null;
    }
    final OhlcvDatasetEntity entity = new OhlcvDatasetEntity();
    entity.setId(dataset.id());
    entity.setDataProvider(dataset.dataProvider());
    entity.setTimeframeSeconds(dataset.timeframe().getSeconds());
    entity.setStartAt(dataset.startAt());
    entity.setEndAt(dataset.endAt());
    entity.setOrderCount(dataset.orderCount());
    entity.setCreatedAt(dataset.createdAt());
    entity.setLongTermStorageKey(dataset.longTermStorageKey());
    entity.setMarketType(dataset.market().type());
    entity.setExchange(dataset.market().exchange());
    entity.setBase(dataset.market().base());
    entity.setQuote(dataset.market().quote());
    return entity;
  }
}
