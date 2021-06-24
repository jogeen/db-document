# db-document
Mysql数据库设计文档生成工具
# 数据库设计文档生成工具DB-Document


为了对接其它数据采集方，对接我们的数据库方便。开发了一个对整库生成Excel格式的 数据库文档的工具
### 桌面程序界面

##### 界面

为了方便使用，用Swing做了一个界面。不熟悉Swing的可以忽略这部分。


![UI.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/a1e07ffa90d544f6b7c0640a364c4590~tplv-k3u1fbpfcp-watermark.image)

##### 使用设计器

![工程.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c641487e2013407eb6d5b65f07d8e596~tplv-k3u1fbpfcp-watermark.image)

### 连接数据库

开发小工具程序，在此不需要引用过重的其它框架。在此使用最自己的JDBC链接。

链接数据库，获取该用户权限能访问的所有数据库名称。

### 生成EXCEL

当我们拿到数据库的结构之后，就可以安装我们想要的格式生成文档了。这里我们生成最常见的Excel格式的文档。

第一页为目录，后面每一页对应一个表的设计

- 效果


![excel.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/9a0d4bb8fa5e403b8ca1fdfc9fe6fb89~tplv-k3u1fbpfcp-watermark.image)


![表格.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e77cea5ae2084d5c8fea8088e8ded967~tplv-k3u1fbpfcp-watermark.image)
