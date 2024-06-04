#! /bin/bash


spring init \
--boot-version=3.2.0 \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=questionms \
--package-name=dev.agasen.question \
--groupId=dev.agasen.agasen.question \
--dependencies=web,mysql,lombok,data-jpa \
--version=0.0.1-SNAPSHOT \
questionms

docker run --name axonserver -p