#!/bin/bash

ontologies=(LUBM semintec vicodi)

# for ontology in $ontologies
for ((i=0;i<3;i++))
do
    ontology=${ontologies[$i]}
    echo "********"$ontology"********"
    for ((m=0;m<3;m++))
    do
        dataset="data"
        if [ $ontology = LUBM ] ; then
            dataset="abel_"$dataset
        fi
        java -Xms8g -Xmx8g -jar drewer.jar -o ../benchmarks/$ontology/"abel_"$ontology".dlp" -d ../benchmarks/$ontology/$dataset -q ../benchmarks/$ontology/abel_queries.dlp -m $m > results/compare/"exp"$i"_m"$m".out"
    echo "m"${m}" done"
    done
    echo "*********************"
    echo ""
done
