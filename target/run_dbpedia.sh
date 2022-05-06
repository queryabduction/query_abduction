#!/bin/bash

queries=../benchmarks/DBpedia/queries_0424.dlp
ontologies=(rules_test11_lr1000 0504_test13_final 0504_test10_final rules_test7_lr5 0504_test8_final 0504_test9_final 0504_test9_final 0504_test9_final)
datasets=(data_test7_5k data_test7_5k data_test7_5k data_test7_5k data_test7_5k data_test7_5k data_test7_10k data_test7_20k)

for ((i=2;i<3;i++))
do
    echo "********"$i"********"
    for ((m=0;m<3;m++))
    do
        java -Xms8g -Xmx8g -jar drewer.jar -o ../benchmarks/DBpedia/${ontologies[$i]}".dlp" -d ../benchmarks/DBpedia/${datasets[$i]} -q $queries -m $m > results/DBpedia/"exp"$i"_m"$m".out"
    echo "m"${m}" done"
    done
    echo "*****************"
    echo ""
done
