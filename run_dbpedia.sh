#!/bin/bash

ontologies=(rules_len2_dep1 rules_len2_dep5 rules_len2_dep10 rules_len3_dep10 rules_len3_dep10 rules_len3_dep10)
datasets=(data_5k data_5k data_5k data_5k data_10k data_20k)

for ((i=0;i<6;i++))
do
    echo "********"$i"********"
    for ((m=0;m<3;m++))
    do
        java -Xms8g -Xmx8g -jar drewer-abd.jar -o ../benchmarks/DBpedia/${ontologies[$i]}".dlp" -d ../benchmarks/DBpedia/${datasets[$i]} -q benchmarks/DBpedia/queries.dlp -m $m > results/DBpedia/"exp"$i"_m"$m".out"
    echo "m"${m}" done"
    done
    echo "*****************"
    echo ""
done
