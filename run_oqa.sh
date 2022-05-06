#!/bin/bash

ontologies=(LSTW semintec vicodi STB-128 ONT-256)

# for ontology in $ontologies
for ((i=0;i<5;i++))
do
    ontology=${ontologies[$i]}
    echo "********"$ontology"********"
    for ((m=0;m<3;m++))
    do
        java -Xms8g -Xmx8g -jar drewer-abd.jar -o benchmarks/$ontology/$ontology".dlp" -d benchmarks/$ontology/data -q benchmarks/$ontology/queries.dlp -m $m > results/OQA/"exp"$i"_m"$m".out"
    echo "m"${m}" done"
    done
    echo "*********************"
    echo ""
done
