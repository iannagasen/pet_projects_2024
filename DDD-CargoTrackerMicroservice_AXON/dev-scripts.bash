#! /bin/bash

spring init \
--boot-version=3.2.0 \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=bookingms \
--package-name=dev.agasen.cargo.booking \
--groupId=dev.agasen.cargo.booking \
--dependencies=web,mysql,lombok,data-jpa \
--version=0.0.1-SNAPSHOT \
bookingms

docker run --name axonserver -p