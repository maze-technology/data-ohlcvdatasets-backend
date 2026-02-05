create table public.ohlcv_datasets
(
  id                     uuid primary key default gen_random_uuid(),
  market_type            varchar(255) not null,
  exchange               varchar(255),
  base                   varchar(255) not null,
  quote                  varchar(255) not null,
  data_provider          varchar(255) not null,
  timeframe_seconds      bigint not null,
  start_at               timestamp with time zone not null,
  end_at                 timestamp with time zone not null,
  order_count            integer not null,
  long_term_storage_key  varchar(255),
  created_at             timestamp with time zone not null default now()
);

create table public.ordered_ohlcvs_data
(
  ohlcv_dataset_id  uuid not null,
  position          integer not null,
  ohlcv_data        bytea not null,
  primary key (ohlcv_dataset_id, position)
);

create index index_ordered_ohlcvs_data_ohlcv_dataset_id
  on ordered_ohlcvs_data(ohlcv_dataset_id);
