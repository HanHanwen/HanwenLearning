for i in $(seq 10)
do
    if [ 0 -eq $[${i}%2] ];then
        echo "${i} 为偶数"
    fi
done