# Maven - 学习笔记

## 1. 简介
### 1.1 基本概念
- Maven 是基于项目对象模型`POM`，可以通过一小段描述信息来管理项目的构建、报告和文档的**软件项目管理工具**；
- 提供了仓库的概念，统一管理`JAR`包，避免出现冲突的现象；
### 1.2 Maven安装目录
- `bin`
	- mvn - 运行脚本
	- m2.conf - 配置文件
- `boot`
	- 类加载器框架
- `conf`
	- settings.xml
- `lib`
	- maven运行需要的类库
	- 第三方依赖的类库

### 1.3 环境变量配置
- `M2_HOME` ： Maven的安装目录；
- `PATH`：增加`M2_HOME\bin`；

``` bash
//验证安装
mvn -version
```

## 2. 项目构建
### 2.1 目录结构
- src
	- main
		- java
			- package
	- test
		- java
			- package
	- resources

