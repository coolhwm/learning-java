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
### 1.4 坐标
- `groupId`：公司名称反写+项目名；最好和包名吻合；
- `artifactId`：项目名-模块名；
- `version`：版本号；

### 1.5 仓库
- 本地仓库
	- 构建项目时，首先会在本地仓库查找，查找不到时会到远程仓库检索；
	- 默认存在当前用户的`.m2`路径下，变更路径需要修改`setting.xml`文件；
```
//本地仓库路径 setting.xml
<localRepository>D:\Development\Library\mvn</localRepository>
```
- 远程仓库
	- 默认远程仓库：`lib\maven-model-builder-3.3.9.jar`中的`pom-4.0.0.xml`中定义；
	- 这个pom文件是`超级pom`，所有pom配置都会继承自这个文件；
	- 在`repository`节点中定义了全球中央仓库`https://repo.maven.apache.org/maven2`；
```
//默认中央仓库配置 pom-4.0.0.xml
<repositories>
	<repository>
		//唯一标识符
		<id>central</id>
		//名称
		<name>Central Repository</name>
		//URL
		<url>https://repo.maven.apache.org/maven2</url>
	</repository>
</repositories>
```
- 镜像仓库
	- 在`setting.xml`中的`mirror`节点配置；
	- 配置在`mirrorOf`的原仓库无法访问，只能访问其镜像仓库；
```
//镜像仓库配置 setting.xml
<mirror>
	//唯一标识符
	<id>central</id>
	//是哪个仓库的镜像(要和默认的一致)
	<mirrorOf>central</mirrorOf>
	//名称
	<name>central</name>
	//地址
	<url>http://192.168.125.134:8081/nexus/content/repositories/central/</url>
</mirror>
```

## 2. 项目构建
### 2.1 目录结构
- maven的目录结构是约定好的，只有按照约定的目录结构才能正常执行maven指令；
```
- src
	- main
		- java
			- package
	- test
		- java
			- package
	- resources
- target
	- classes
	- test-classes
```

### 2.2 pom.xml
- `modelVersion`：版本，固定为`4.0.0`；
- `groupId`：项目的包名；使用点符号分隔；
- `artifactId`：模块名、项目名；使用中划线分隔；
- `version`：版本号；
- `dependencies`
	- `dependency`：依赖包；
		- `groupId`
		- `artifactId`
		- `version`

### 2.3 常用命令
- `mvn compile` - 编译项目
	- 编译前会自动下载相关插件和依赖；
	- java文件会编译到`target\classes`文件夹下；
- `mvn test` - 测试项目
	- 测试会执行`test`目录下的所有测试用例；
	- 测试用例会编译到`target\test-classes`文件夹下；
	- 测试报告在`target\surefire-reports`文件夹下；
- `mvn package` - 打包项目
	- 在`target`目录下生成`jar`包； 
- `mvn clean` - 清理项目
	- 删除项目的`target`文件夹；
- `mvn install` - 发布到本地仓库
	- 将项目编译、打包、并发布到本地仓库中；

### 2.4 archetype 
- `mvn -archetype:generate`
	- 按照页面提示，输入`groupId`,`artifactId`,`version`,`package`；
	- 自动生成项目骨架、`pom.xml`；
```
//直接生成
-DgroupId=组织名,公司名+项目名
-DartifactId=项目名
-Dversion=版本号
-Dpackage=包名
```

## 3. 生命周期
### 3.1 项目构建过程
清理、 编译、测试、打包、集成测试、验证、部署

### 3.2 Maven的生命周期
执行每个阶段中的步骤时，总会先执行这个阶段的前序步骤。
- clean：清理项目
	- pre-clean
	- clean
	- post-clean
- default：构建项目
	- compile
	- test
	- package
	- install
- site：生成项目站点；
	- pre-site
	- site
	- post-site
	- site-deploy

### 3.3 插件
- Maven本身不包含编译等功能，这些功能是通过插件来实现的；
- 配置插件可以使Maven实现一些额外的功能；
```
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-source-plugin</artifactId>
            <version>2.4</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>
                            jar-no-fork
                        </goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

