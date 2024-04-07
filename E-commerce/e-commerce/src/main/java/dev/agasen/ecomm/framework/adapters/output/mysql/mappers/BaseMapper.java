package dev.agasen.ecomm.framework.adapters.output.mysql.mappers;

public interface BaseMapper<DATA, DOMAIN> {

  DATA toDataModel(DOMAIN domain);

  DOMAIN toDomainModel(DATA data);
}
