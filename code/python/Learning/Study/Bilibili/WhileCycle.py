# coding:UTF-8
print("Hello world")

# 单行注释

'''
多行注释1
'''

"""
多行注释2
"""

print("Yep")

numbers = [1, 2, 3, 4, 5, 6]
even = []
odd = []

while len(numbers) > 0:
    number = numbers.pop()
    if number % 2 == 0:
        even.append(number)
    else:
        odd.append(number)
print(even)
print(odd)

# continue和break的用法
i = 1
while i < 10:
    i += 1
    if i % 2 > 0:
        continue
    print("i=", i)

j = 1
while 1:
    # 循环条件为1，则必然成立
    print("j=", j)
    j += 1
    if j > 10:
        break

# while else
count = 0
while count < 5:
    print(count, " is less then 5")
    count += 1
else:
    print(count, " is not less then 5")

