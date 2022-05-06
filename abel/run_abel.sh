benchs=(LSTW Semintec Vicodi)
tboxes=(lubm-ex-20 SEMINTEC vicodi_all)
aboxes=(LSTW-ELH-1univ SEMINTEC vicodi_all)

for ((i=0;i<3;i++))
do
    bench=${benchs[$i]}
    tbox=${tboxes[$i]}
    abox=${aboxes[$i]}

    result=$bench/"res_"$bench".txt"
    if [ -f $result ] ; then
    rm $result
    fi

    echo "********"$bench"********"
    java -Xms8g -Xmx8g -jar ABEL.jar -queries $bench/queries.txt -allU -allB -constantAb $bench/individual.txt -tbox $bench/$tbox".owl" -abox $bench/$abox".owl" -xsb /home/bohe/XSB/config/x86_64-unknown-linux-gnu/bin/xsb -limit 20 -result $result
    echo ""
done