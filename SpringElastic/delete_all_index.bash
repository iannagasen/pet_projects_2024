#!/bin/bash

indices=$(curl -s 'http://localhost:9200/_cat/indices?h=index')

for index in $indices;
do
  echo "Deleting index: $index";
  curl -X DELETE "http://localhost:9200/${index}";
  echo;
done;