# 刷软考在线开通免费题库的抵扣ip数

一年两度的软考又快到了，还记得大学的时候，考软考前班群都是软考在线求点击的消息，刚好我也要考软考，昨天朋友跟我吐槽刷这个ip抵扣数好麻烦，就弄个脚本刷一下，just do it。

阿西吧，还有流量精灵这种东西，增加多一个方法。
# 方法一
下载流量精灵软件，会报毒，我在虚拟机跑的，打开后添加网址
![]([img]https://i.loli.net/2019/04/28/5cc539c7b08c6.png[/img])
```
http://www.rkpass.cn/addtrace.jsp?url_save=http://www.rkpass.cn/u.jsp___u=换成自己的专属号码
```
添加完后流量精灵会自己跑起来了。

# 方法二
跑程序，爬西刺的免费ip，软考在线（[http://www.rkpass.cn](http://www.rkpass.cn)）要求不同ip点击就算抵扣数，那需要先爬去大量的ip来做代{过}{滤}理，然后请求软考在线的ip记录接口。


我爬的是免费的ip代理，所以有很多会请求失败，就挂着程序在那里跑好了，不要急。

## 程序使用方法
代码地址[https://github.com/HeyKin/AddRkPageView](https://github.com/HeyKin/AddRkPageView)，代码环境是JDK8+eclipse+maven

一 获取自己在软考在线的专属id，也就是分享链接u=后面的一串数字

![]([img]https://i.loli.net/2019/04/26/5cc2adc9d321a.png[/img])

二 更改`IpCrawler.java`文件中的RK_URL常量，把id替换为自己的id

![]([img]https://i.loli.net/2019/04/26/5cc2ae67def3c.png[/img])

三 关于TOTAL_PAGE常量，这个是爬多少页西刺代{过}{滤}理的ip，共有1981页，按自己的需求更改

![]([img]https://i.loli.net/2019/04/26/5cc2aeed2d2cc.png[/img])



# 最后
代码比较简单，可能有问题，若有需要优化的地方，各位优化一下，祝大家逢考必过！
