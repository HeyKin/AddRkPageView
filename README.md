# 刷软考在线的抵扣ip数

还记得大学的时候，考软考前班群都是软考在线求点击的消息，刚好今年我也要考软考，昨天朋友跟我吐槽刷这个ip抵扣数好麻烦，我就想我为嘛要让人点，自己弄个脚本刷就好啦，just do it。

软考在线（[http://www.rkpass.cn](http://www.rkpass.cn)）要求不同ip点击就算抵扣数，那需要先爬去大量的ip来做代理，然后请求软考在线的ip接口。

# 程序使用方法

一 获取自己在软考在线的专属id，也就是分享链接**u=**后面的一串数字

![](https://i.loli.net/2019/04/26/5cc2adc9d321a.png)

二 更改`IpCrawler.java`文件中的RK_URL常量，把id替换为自己的id

![](https://i.loli.net/2019/04/26/5cc2ae67def3c.png)

三 关于TOTAL_PAGE常量，这个是爬多少页西刺代理的ip，共有1981页，按自己的需求更改

![](https://i.loli.net/2019/04/26/5cc2aeed2d2cc.png)



# 最后祝大家逢考必过

