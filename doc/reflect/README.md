# Java 反射 - 学习笔记

## Class 类
- 万物皆为对象
	- 在`Java`语言中，静态成员、普通数据类型不是面向对象；
	- 类本身也是对象，是`java.lang.Class`类的实例对象；
- 类对象如何表示
	- 无法直接创`Class`的实例对象，它的构造方法是私有的，只能由虚拟机调用；
	- 任何一个类都是`Class`类的实例对象；
	- 类的实例对象，称为`类类型`（`Class Type`）；
	- 一个类只能是`Class`类的一个实例对象，通过多种方式获得类类型是一个对象；
	- 基本类型、包装类型、`void`都存在类类型；
- 类类型的获取方式
	- `Foo.class`：任何一个类都一个隐含的静态成员变量`class`；
	- `fooo.getClass()`：通过已知的类对象的`getClass`方法；返回实例实际子类的类类型；
	- `Class.forName()`：通过字符串获得类类型；还代表动态加载类；
- 可以通过类类型创建对象
	- `newInstance()`：需要有无参数的构造方法；
- 动态加载类
	- 可以使用`Class.forName()`动态加载类；
	- 编译时刻加载的类是`静态加载类`，运行时刻加载的类是`动态加载类`；
	- 动态加载类利于模块化，若动态加载的类不存在，在编译时不会报错；
	- 利于动态加载、更新模块，而不需要修改主体框架，不需要重新编译；
- 基本API操作
	- `getName()`：获取类的全名；
	- `getMethods()`：获取所有`public`方法，包括从父类继承的；
	- `getDeclareMethods()`：获取所有自己声明的方法，不限访问权限；
	- `getFields()`：获取所有`public`成员变量，包括从父类继承的；
	- `getDeclareFields()`：获取所有自己声明的成员变量，不限访问权限；
	- `getConstructors()`：获取所有的构造方法；
	- `getDeclaredConstructors()`：获取所有自己声明的构造方法；
	- **类的各种信息都可以通过类类型获得**；

``` java
//获取类类型
Class<Foo> c1 = Foo.class;
Class<? extends Foo> c2 = foo.getClass();
Class<?> c3 = Class.forName("learning.java.reflect.demo.Foo");
```

## Method 类
- 基本概念
	- 方法是`java.lang.reflect.Method`的对象实例；
	- 一个成员方法就是一个`Method`对象；
- 基本API操作
	- `getNmae()`：获取方法名称；
	- `getReturnType()`：获取返回值的类类型；
	- `getParameterTypes()`：获取参数列表的类类型；

``` java
//获方法信息
Method[] methods = c.getMethods();
Method[] declaredMethods = c.getDeclaredMethods();
Class<?>[] parameterTypes = method.getParameterTypes();
```
## Filed 类
- 基本概念
	- 成员变量是`java.lang.reflect.Field`的对象实例；
	- 一个成员变量就是一个`Method`对象；
- 基本API操作
	- `getType()`：获取成员变量的类类型；
	- `field.getName`：获取成员变量的名称；

``` java
//字段信息
Class<?> type = field.getType();
String typeName = type.getName();
String fieldName = field.getName();
```

## Constructor 类
- 基本概念
	- 构造方法是`java.lang.reflect.Constructor`的对象实例；
	- 一个方法就是一个`Constructor`对象；
- 基本API操作
	- `getName()`：构造方法名称；
	- `getParameterTypes()`：构造方法参数列表；
``` java
//构造方法信息
Class<?>[] parameterTypes = constructor.getParameterTypes();
```