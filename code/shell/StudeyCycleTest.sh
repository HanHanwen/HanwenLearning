#1.提示用户输入一个数字
#2.判断用户输入的值是否等于18
#3.如果用户输入的值等于18，则退出程序
#4.如果用户输入的值不等于18，则允许用户尝试3次
#5.若是用户超过3次，则再次询问是否继续，若是输入y，就再次给3次机会

count=0

while [ ${count} -lt 3 ]
do
    read -p "Please input a number:" num
    if [ ${num} -eq 18 ];then
        echo "Your number is right"
        break
    else
        echo "Your number is error, please confirm"

        let count++
    fi
done


while [ ${count} -eq 3 ]
do
    read -p "Do you want to try again?[y|n]:" result

    if [ ${result} = y ];then
        while [ ${count} -lt 6 ]
        do
            read -p "Please input a number:" num
            if [ ${num} -eq 18 ];then
                echo "Your number is right"
                break
            else
                echo "Your number is error, please confirm"
        
                let count++
            fi
        done
    else
        echo "bye~"
        break
    fi 
done