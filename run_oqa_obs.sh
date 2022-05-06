#!/bin/bash

ontologies=(LUBM semintec vicodi STB-128 ONT-256)

# for ontology in $ontologies
for ((i=3;i<5;i++))
do
    ontology=${ontologies[$i]}
    echo "********"$ontology"********"
    for ((m=0;m<3;m++))
    do
        dataset="data"
        if [ $ontology = LUBM ] ; then
            dataset=$dataset"001"
        fi
        java -Xms8g -Xmx8g -jar drewer.jar -o ../benchmarks/$ontology/$ontology".dlp" -d ../benchmarks/$ontology/$dataset -q ../benchmarks/$ontology/obs_queries.dlp -m $m > results/OBS/"exp"$i"_m"$m".out"
    echo "m"${m}" done"
    done
    echo "*********************"
    echo ""
done
