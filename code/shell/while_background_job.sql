i=1
while [ ${i} -lt 5 ]
do
    let i++
    echo "hello"
    {
        sleep 3    #休眠3秒
        echo "当前数字为：${i}"
    }& #后台执行
done