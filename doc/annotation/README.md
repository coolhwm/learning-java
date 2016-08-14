# Java 注解 - 学习笔记

## 1. 简介
> `Java`提供了一种原程序中的元素关联任何信息和任何元素的途径和方法

## 2. 常见注解

### 2.1 JDK自带注解
- `@Deprecated`：标注过时方法或类；
- `@Override`：必须覆盖或实现父类的方法；
- `@SuppressWarnings`：消除警告；

### 2.2 常见第三方注解
在常见框架中使用注解，可以简化`XML`配置，让配置框架更加简单。
- Spring 框架
	- `@Autowired`
	- `@Service`
	- `@Repository`
- MyBatis 框架
	- `@InsertProvider`
	- `@updateProvider`
	- `@Options`

## 3. 注解分类
- 按照运行机制分类
	- 源码注解：只在源码中存在，编译成`.class`文件就不存在了；
	- 编译时注解：在源代码和`.class`文件中都存在；
	- 运行时注解：在运行阶段会起作用，甚至会影响运行逻辑的注解；
- 按照注解来源分类
	- JDK注解；
	- 第三方注解；
	- 自定义注解；
	- 元注解`meta-annotation`：给注解进行注解；

## 4. 自定义注解
### 4.1 语法要求
- 使用`@interface`声明是一个注解；
- 成员参数以无参数，无异常的方式声明；
- 可以使用`default`为成员指定一个默认值；
- 成员类型受限，仅为基本类型,`String`,`Class`,`Annotation`,`Enumeraation`；
- 如果注解只有一个成员，则成员取名最好为`value()`，使用时可以不指定参数名；
- 注解类可以没有成员，称为标示注解；
- 注解需要用元注解修饰；

```
@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description {
    String desc() default "";
    String author();
    int age();
}

```

### 4.2 元注解
- `@Traget`：指定注解的作用域；
	- `ANNOTATION_TYPE`
	- `CONSTRUCTOR`
	- `FIELD`
	- `LOCAL_VARIABLE`
	- `METHOD`
	- `PACKAGE`
	- `PARAMETER`
	- `TYPE`
- `@Retention`：指定注解的生命周期；
	- `CLASS`
	- `RUNTIME`
	- `SOURCE`
- `@Inheried`：若使用注解的类，其存在子类，那么子`类的类作用域注解`会继承父类的注解；
- `@Documented`：注解表明这个注解应该被 javadoc工具记录；

### 4.3 使用注解
```
@Description(age = 18, author = "213")
public void sing(){}
```

### 4.4 解析注解
> 通过反射获取类、函数或成员上的**运行时注解**信息，从而实现动态控制程序运行的逻辑。

- 可以在`Class`,`Method`,`Filed`等反射对象上调用方法解析注释：
	- `isAnnotationPresent`：判断是否存在某注解；
	- `getAnnotation`：根据注解类型获取注解对象；
	- `getAnnotations`：获取所有的注解；
- 只有`RUNTIME`注解才可以在运行时进行解析；
