fruits = ['apple', 'banana', 'mango']
for fruit in fruits:
    print(fruit)

# 通过序列索引迭代
for index in range(len(fruits)):
    print(fruits[index])


# for else
# 查找数字的全部因子
factor = []
for num in range(10, 20):
    # 选取10到20的数字
    factor.clear()
    for i in range(2, num):
        # 根据因子进行迭代
        factor.append(i)
        if num % i == 0:
            j = num/i
            if factor.__contains__(j) and i != j:
                break
            else:
                print('%d = %d * %d' % (num, i, j))
        i += 1
    else:
        print(num, '是一个质数')

# factor = [2, 3]
# if factor.__contains__(2):
#     print('True')
# else:
#     print('False')
