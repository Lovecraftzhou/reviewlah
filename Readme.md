# 项目名称
**Reviewlah**

# 环境搭建

1. JDK安装: JDK 17

2. Maven安装:在review-sys中的maven文件夹下运行**mvnw**文件,
mvnw文件适用于Linux(bash), mvnw.cmd适用于Windows环境


# 技术选型
| 技术                  | 版本      | 说明                           |
|---------------------|---------|------------------------------|
| Spring Boot         | 3.1.4   | MVC核心框架                      |
| MyBatis             | 3.5.13  | ORM框架                        |
| MyBatisPlus         | 3.5.3.2 | 基于mybatis，使用lambda表达式的       |


# 目录结构

~~~
reviewlah
├── reviewlah-sys -- 项目系统配置文件
├── reviewlah-back -- 后端工程
    ├── admin  后台（vue）接口工程
    ├── common 前后台需要用到的公共配置，工具类等的集合地
    ├── service 前后台需要用到的公共的、商城基本流程所需的service，dao的集合地
├── fronted-vue -- 前端(Vue)工程
~~~
