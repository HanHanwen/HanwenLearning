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