#变量类型：
#   环境变量：“env”查看当前用户的环境变量
        # 定义环境变量：
        #     1.export back_dir2="hello"
        #     2.export back_dir1    将自定义变量转换成环境变量，无论自定义变量是否实现被声明都可以进行转换，只是自定义变量若是事先未被声明，则无意义
        # 引用环境变量：
        #     $变量名   或    ${变量名}
        # 查看变量：
        #     echo $变量名
        # 取消环境变量：
        #     unset 变量名
        # 变量作用范围：
        #     仅当前shell和子shell有效
➜  shell git:(master) ✗ lihanwen_back=test
➜  shell git:(master) ✗ echo $lihanwen_back
test
➜  shell git:(master) ✗ bash

The default interactive shell is now zsh.
To update your account to use zsh, please run `chsh -s /bin/zsh`.
For more details, please visit https://support.apple.com/kb/HT208050.
bash-3.2$ echo $lihanwen_back

bash-3.2$ exit
exit
➜  shell git:(master) ✗ export lihanwen_back
➜  shell git:(master) ✗ bash

The default interactive shell is now zsh.
To update your account to use zsh, please run `chsh -s /bin/zsh`.
For more details, please visit https://support.apple.com/kb/HT208050.
bash-3.2$ echo $lihanwen_back
test
bash-3.2$ exit
exit
➜  shell git:(master) ✗ env
#其他信息省略
lihanwen_back=test



#   自定义变量：
#   位置变量：$1 $2 ${10}
        # $0    内置的位置参数，用以标示任务名
#   预定义变量：
        # $0    脚本名
        # $*    所有参数
        # $@    所有参数
        # $#    参数个数
        # $$    当前进程PID
        # $!    上一个后台进行的PID
        # $?    上一个命令的返回值
        #           0：表示成功
        #           1/2：权限拒接
        #           1～125：表示运行失败，脚本命令、系统命令错误或参数传递错误
        #           126：找到命令但是无法执行
        #           127：命令找不到
        #           >128：命令执行中退出
➜  shell git:(master) ✗ name=shark
➜  shell git:(master) ✗ echo $name
shark
➜  shell git:(master) ✗ echo ${name}
shark
➜  shell git:(master) ✗ echo ${#name}
5




#强引和弱引
    # 强引用：''    单引号内是什么则引用什么，无法判断变量
    # 弱引用：""    会对双引号内的变量$进行判断