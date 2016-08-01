# Java ���� - ѧϰ�ʼ�

## Class ��
- �����Ϊ����
	- ��`Java`�����У���̬��Ա����ͨ�������Ͳ����������
	- �౾��Ҳ�Ƕ�����`java.lang.Class`���ʵ������
- �������α�ʾ
	- �޷�ֱ�Ӵ�`Class`��ʵ���������Ĺ��췽����˽�еģ�ֻ������������ã�
	- �κ�һ���඼��`Class`���ʵ������
	- ���ʵ�����󣬳�Ϊ`������`��`Class Type`����
	- һ����ֻ����`Class`���һ��ʵ������ͨ�����ַ�ʽ�����������һ������
	- �������͡���װ���͡�`void`�����������ͣ�
- �����͵Ļ�ȡ��ʽ
	- `Foo.class`���κ�һ���඼һ�������ľ�̬��Ա����`class`��
	- `fooo.getClass()`��ͨ����֪��������`getClass`����������ʵ��ʵ������������ͣ�
	- `Class.forName()`��ͨ���ַ�����������ͣ�������̬�����ࣻ
- ����ͨ�������ʹ�������
	- `newInstance()`����Ҫ���޲����Ĺ��췽����
- ��̬������
	- ����ʹ��`Class.forName()`��̬�����ࣻ
	- ����ʱ�̼��ص�����`��̬������`������ʱ�̼��ص�����`��̬������`��
	- ��̬����������ģ�黯������̬���ص��಻���ڣ��ڱ���ʱ���ᱨ��
	- ���ڶ�̬���ء�����ģ�飬������Ҫ�޸������ܣ�����Ҫ���±��룻
- ����API����
	- `getName()`����ȡ���ȫ����
	- `getMethods()`����ȡ����`public`�����������Ӹ���̳еģ�
	- `getDeclareMethods()`����ȡ�����Լ������ķ��������޷���Ȩ�ޣ�
	- `getFields()`����ȡ����`public`��Ա�����������Ӹ���̳еģ�
	- `getDeclareFields()`����ȡ�����Լ������ĳ�Ա���������޷���Ȩ�ޣ�
	- `getConstructors()`����ȡ���еĹ��췽����
	- `getDeclaredConstructors()`����ȡ�����Լ������Ĺ��췽����
	- **��ĸ�����Ϣ������ͨ�������ͻ��**��

``` java
//��ȡ������
Class<Foo> c1 = Foo.class;
Class<? extends Foo> c2 = foo.getClass();
Class<?> c3 = Class.forName("learning.java.reflect.demo.Foo");
```

## Method ��
- ��������
	- ������`java.lang.reflect.Method`�Ķ���ʵ����
	- һ����Ա��������һ��`Method`����
- ����API����
	- `getNmae()`����ȡ�������ƣ�
	- `getReturnType()`����ȡ����ֵ�������ͣ�
	- `getParameterTypes()`����ȡ�����б�������ͣ�

``` java
//�񷽷���Ϣ
Method[] methods = c.getMethods();
Method[] declaredMethods = c.getDeclaredMethods();
Class<?>[] parameterTypes = method.getParameterTypes();
```
## Filed ��
- ��������
	- ��Ա������`java.lang.reflect.Field`�Ķ���ʵ����
	- һ����Ա��������һ��`Method`����
- ����API����
	- `getType()`����ȡ��Ա�����������ͣ�
	- `field.getName`����ȡ��Ա���������ƣ�

``` java
//�ֶ���Ϣ
Class<?> type = field.getType();
String typeName = type.getName();
String fieldName = field.getName();
```

## Constructor ��
- ��������
	- ���췽����`java.lang.reflect.Constructor`�Ķ���ʵ����
	- һ����������һ��`Constructor`����
- ����API����
	- `getName()`�����췽�����ƣ�
	- `getParameterTypes()`�����췽�������б�
``` java
//���췽����Ϣ
Class<?>[] parameterTypes = constructor.getParameterTypes();
```