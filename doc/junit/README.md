# jUnit - 学习笔记

## 1. 简介
- 什么是`jUnit`
> `JUnit` is a simple framework to write repeatable tests. It is an instance of the `xUnit` architecture for unit testing frameworks.
> `JUnit` 是一个编写可复用测试用例的简单框架，是单元测试框架`xUnit`的一个子集。

- 为什么使用`jUnit`
	- 帮助对程序进行有目的性的测试；
	- 最大限度的避免代码中的BUG，达到预期的效果；
	- 相比`main`方法测试更为优化，更加规范；
	- 使用断言机制实现自动化测试，而不是通过观察`print`的输出结果进行测试；

## 2. 使用方法
### 2.1 最佳实践
- 测试方法必须使用`@Test`修饰；
- 测试方法必须使用`public void`修饰，不能带任何参数；
- 新建一个源代码目录存放测试代码；
- 测试类的包应该和被测试类的包保持一致；
- 测试单元中的每个方法必须可以独立测试，测试方法间不能有任何的依赖；
- 测试类使用`Test`作为类名的后缀；
- 测试方法使用`test`作为方法名的前缀；
