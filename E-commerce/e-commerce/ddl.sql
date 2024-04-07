create table product (
  id bigint not null,
  updated_by varchar(255),
  created_at datetime(6),
  updated_at datetime(6),
  created_by varchar(255),
  description varchar(255),
  title varchar(255),
  image BLOB,
  primary key (id)
) engine = InnoDB create table product_seq (next_val bigint) engine = InnoDB;