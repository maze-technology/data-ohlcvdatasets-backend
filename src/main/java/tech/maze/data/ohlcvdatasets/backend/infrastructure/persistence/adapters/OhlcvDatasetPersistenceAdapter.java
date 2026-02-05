package tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.adapters;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OhlcvDataset;
import tech.maze.data.ohlcvdatasets.backend.domain.models.OrderedOhlcvData;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.out.LoadOhlcvDatasetPort;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.out.LoadOrderedOhlcvDataPort;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.out.SaveOhlcvDatasetPort;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.out.SaveOrderedOhlcvDataPort;
import tech.maze.data.ohlcvdatasets.backend.domain.ports.out.SearchOhlcvDatasetsPort;
import tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.entities.OhlcvDatasetEntity;
import tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.entities.OrderedOhlcvDataEntity;
import tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.mappers.OhlcvDatasetEntityMapper;
import tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.mappers.OrderedOhlcvDataEntityMapper;
import tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.repositories.OhlcvDatasetJpaRepository;
import tech.maze.data.ohlcvdatasets.backend.infrastructure.persistence.repositories.OrderedOhlcvDataJpaRepository;

/**
 * Persistence adapter for OHLCV datasets and ordered data.
 */
@Component
@RequiredArgsConstructor
public class OhlcvDatasetPersistenceAdapter implements LoadOhlcvDatasetPort,
    SearchOhlcvDatasetsPort,
    SaveOhlcvDatasetPort,
    LoadOrderedOhlcvDataPort,
    SaveOrderedOhlcvDataPort {
  private final OhlcvDatasetJpaRepository ohlcvDatasetJpaRepository;
  private final OrderedOhlcvDataJpaRepository orderedOhlcvDataJpaRepository;
  private final OhlcvDatasetEntityMapper ohlcvDatasetEntityMapper;
  private final OrderedOhlcvDataEntityMapper orderedOhlcvDataEntityMapper;

  @Override
  public Optional<OhlcvDataset> findById(UUID id) {
    return ohlcvDatasetJpaRepository.findById(id).map(ohlcvDatasetEntityMapper::toDomain);
  }

  @Override
  public List<OhlcvDataset> findAll() {
    return ohlcvDatasetJpaRepository.findAll().stream()
        .map(ohlcvDatasetEntityMapper::toDomain)
        .toList();
  }

  @Override
  public OhlcvDataset save(OhlcvDataset dataset) {
    final OhlcvDatasetEntity entity = ohlcvDatasetEntityMapper.toEntity(dataset);
    return ohlcvDatasetEntityMapper.toDomain(ohlcvDatasetJpaRepository.save(entity));
  }

  @Override
  public List<OrderedOhlcvData> findByDatasetId(UUID datasetId) {
    return orderedOhlcvDataJpaRepository.findByOhlcvDatasetIdOrderByPositionAsc(datasetId)
        .stream()
        .map(orderedOhlcvDataEntityMapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<OrderedOhlcvData> saveAll(List<OrderedOhlcvData> orderedData) {
    final List<OrderedOhlcvDataEntity> entities = orderedData.stream()
        .map(orderedOhlcvDataEntityMapper::toEntity)
        .collect(Collectors.toList());
    return orderedOhlcvDataJpaRepository.saveAll(entities).stream()
        .map(orderedOhlcvDataEntityMapper::toDomain)
        .collect(Collectors.toList());
  }
}
