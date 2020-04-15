本篇主要介绍`iTerm`的别名使用方法：



1.编辑文件`.bash_profile`（没有则新建一个）

```shell
alias gateway='ssh -p xxxx xxx.xx.xx.xx'
```



2.为了使得每次打开新的窗口与重启`iTerm`后别名依旧生效，编辑文件`.zshrc`

```shell
#最后一行加上，此处使用绝对路径更加安全
source /Users/xxx/.bash_profile
```



处理完毕后，加载文件即可；且每次重启`iTerm`后将会自动加载`.bash_profile`，即别名可以使用愉快使用