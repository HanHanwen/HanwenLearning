read -p "Please input a number:" num
if [ ! -z "${num}" ];then
    echo "Ok, the number is ${num},\n now we will compare"
    if [ "${num}" -gt 18 ];then
        echo "This number is larger 18"
    elif [ "${num}" -lt 18 ];then
        echo "this number is littler 18"
    else
        echo "Do not Judge"
    fi
elif [ -z "${num}" ];then
    echo "Please input a number!"
fi