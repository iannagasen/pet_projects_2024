#!/bin/bash

ROOT_PKG="dev.agasen"
CATALOG_SERVICE="catalog-service"

spring init \
  --boot-version=3.2.0 \
  --type=gradle-project \
  --java-version=17 \
  --packaging=jar \
  --name=catalog-service \
  --package-name="dev.agasen.catalog" \
  --groupId="dev.agasen.catalog" \
  --dependencies=data-elasticsearch,web \
  --version=1.0.0-SNAPSHOT \
  catalog-service