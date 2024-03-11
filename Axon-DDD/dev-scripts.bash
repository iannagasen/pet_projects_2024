#!/bin/bash

spring init \
--boot-version=3.2.0 \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=product-service \
--package-name=dev.agasen.productservice \
--groupId=dev.agasen.productservice \
--dependencies=web,mysql,lombok,data-jpa \
--version=0.0.1-SNAPSHOT \
product-service

docker run --name axonserver -p         
