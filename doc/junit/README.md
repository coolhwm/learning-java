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

``` java
//简单的测试用例
public class DataTester {
    @Test
    public void testAdd(){
		//...
    }
}
```

### 2.2 测试失败的类型
- `Failure`：一般由于单元测试使用的断言方法判断失败所引起，说明测试点发现了问题，程序输出的结果和我们的预期不一致；
- `Error`：由于代码的异常引起的，可能产生于测试代码本身的错误，也可以是测试代码中的一个`BUG`；

> 测试用例不是证明你是对的，而是证明你没有错

### 2.3 运行过程
- `@BeforeClass`：静态方法，在所有方法被调用前被执行，适合用于加载配置文件；
- `@Before`：在每个测试用例执行前被调用；
- `@Test`：测试用例；
- `@After`：在每个测试用例执行后被调用；
- `@AfterClass`：静态方法，在所有方法被调用后调用，适合用于清理资源，例如关闭数据库；

### 2.4 常用注解
- `@Test`：单元测试；
	- `expected`：期待抛出的异常；
	- `timeout`：超时期限，对方法执行的时间进行限定，进行性能测试；
- `@Ignore`：忽略单元测试；
	- `value`：注释信息；
- `@RunWith`：更改测试运行期；
	- `value`：测试运行器的`class`；
	- 测试运行期需要继承自`org.junit.runner`；

### 2.5 测试断言
- `assertArrayEquals`：数组相等；
- `assertEquals`/`assertNotEquals`：对象相等；
- `assertNull`/`assertNotNull`：空值判断；
- `assertSame`/`assertNotSame`：引用相等；
- `assertTrue`/`assertFalse`：布尔判断；

### 2.6 测试套件
- 测试套件是组织测试类运行的；
- 测试套件必须是一个空类；
- 使用`@RunWith(Suite.class)`指定其测试运行器为测试套件；
- 使用`@Suite.SuiteClasses()`指定测试套件包含的测试类作为数组传入；
- 测试条件中可以包含测试套件；
``` java
//测试套件
@RunWith(Suite.class)
@Suite.SuiteClasses({Test1.class, Test2.class,Test3.class})
public class SuiteTest {
}
```

### 2.7 参数化测试
- 更改默认运行器为`@RunWith(Parameterized.class)`；
- 声明变量来存放预期值和结果值；
- 声明一个返回值为`Collection`的公共静态方法， 并用`@Parameters`进行修饰；
- 为测试类声明一个带有参数的公共构造方法，并在其中为之声明变量赋值；

``` java
//参数化测试
@RunWith(Parameterized.class)
public class ParameterTest {

    int expected = 0;
    int input1 = 0;
    int input2 = 0;
    
    @Test
    public void testAdd(){
        Assert.assertEquals(expected, new Calculate().add(input1,input2));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> t(){
        return Arrays.asList(new Object[][]{
                {3,1,2},
                {4,2,2}
        });
    }

    public ParameterTest(int expected, int input1, int input2) {
        this.expected = expected;
        this.input1 = input1;
        this.input2 = input2;
    }
}
```