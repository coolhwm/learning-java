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
		- resources
	- test
		- java
			- package
- target
	- classes
	- test-classes
```

### 2.2 pom.xml

> Project Object Model，项目对象模型。通过xml格式保存的pom.xml文件。作用类似ant的build.xml文件，功能更强大。该文件用于管理：源代码、配置文件、开发者的信息和角色、问题追踪系统、组织信息、项目授权、项目的url、项目的依赖关系等等。

#### 2.2.1 基本结构
- `project` ：根元素；
	- `modelVersion`：版本，固定为`4.0.0`；
	- `groupId`：项目的包名；使用点符号分隔；公司名称反写+项目名；最好和包名吻合；
	- `artifactId`：模块名、项目名；使用中划线分隔；项目名-模块名；
	- `version`：版本号；`<主版本>.<次版本>.<增量版本>`；
		- `SNAPSHOT`：快照版本；
		- `ALPHA`：内侧版本；
		- `BEAT`：公测版本；
		- `RELEASE`：稳定版本；
		- `GA`：正式发布；
	- `packaging`：打包方式，包含`JAR`,`WAR`,`POM`等；
	- `name`：项目的描述名称；
	- `url`：项目地址；
	- `description`：项目描述；
	- `developers`：开发人员信息；
	- `licenses`：许可信息；
	- `orginazation`：组织信息；
	- `dependencies`：依赖列表；
		- `dependency`：依赖包；
			- `groupId`
			- `artifactId`
			- `version`
			- `type`
			- `scope`：依赖的范围
			- `optional`：是否可选；`true`或`false`，
			- `exclusions`：排除依赖传递列表；
	- `dependencyManagement`：依赖管理；把依赖定义在父模块中，在子模块中可以继承；
	- `build`：对构建行为提供支持；
		- `plugins`：插件类表；
			- `plugin`：插件；
				- `groupId`；
				- `artifactId`；
				- `version`；
	- `parent`：在子模块中继承父模块的`POM`；
	- `modules`：聚合运行多个maven项目；
		- `module`：模块；

#### 2.2.2 依赖的范围
依赖的范围通过`pom.xml`中`dependency`的`scope`属性定义；
- `compile`：在编译、测试、运行都有效，是默认的取值；
- `provided`：在编译和测试有效，在运行时不包括，典型例子为`sevlet`API在容器中有提供，运行时不需要包含；
- `runtime`：只在运行、测试时有效；典型是测试或运行时才需要`JDBC`具体的驱动依赖；
- `test`：只在测试时有效；
- `system`：编译和测试时有效，与本地系统相关联；
- `import`：导入范围，只用在`dependencyManagement`中，表示从其他pom中导入`dependency`的配置；

#### 2.2.3 依赖的传递
- A依赖B，B依赖C，那么A传递依赖C；
- 在A中加入B的依赖，Maven会发现传递依赖，自动解析并加入C的依赖；
- 在`exclusions`中可以定义需要取消传递依赖的坐标；

#### 2.2.4 依赖冲突
- 基本概念
	- 若A和B同时依赖不同版本的C，那么Maven需要确定具体依赖哪个版本的C构件；
- 冲突处理原则
	- 短路由优先：优先解析路径较短的版本；
	- 先声明优先：相同的路径长度，谁的依赖声明靠前，就依赖哪个版本；

#### 2.2.5 聚合项目
- 将一个pom作为其他的pom的容器，`install`会分别编译、测试、安装所有的模块；
- 将`package`属性设置为`pom`；
- 使用`modules`将多个模块包含在内；

#### 2.2.6 抽象项目
- 可以将依赖定义到一个`全局pom`中，子pom可以继承父pom；
- 使用`dependencyManagement`在父pom中定义公共的依赖；
- 使用`parent`应用父项目的坐标；

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
*如何自定义archetype？*

## 3. 生命周期
### 3.1 项目构建过程
清理、 编译、测试、打包、集成测试、验证、部署

### 3.2 Maven的生命周期
执行每个阶段中的步骤时，总会先执行这个阶段的前序步骤。
```
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
```
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

## 4. 构建Web项目
- 使用`maven-archetype-webapp`初始化项目；
- 导入`javax.servlet`的依赖，并将`scope`设置为`provided`；
- 配置`packaging`为`war`；
- 配置Web项目的Facets；
- 配置输出路径；
- 使用Maven插件可以直接`jetty`,`Tomcat`等Web容器；

