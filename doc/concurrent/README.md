# Java 多线程 - 学习笔记

## 1. 基本概念
- 进程
	- 程序的执行过程，是动态性的；
	- 持有资源（共享内存，共享文件）和线程；
- 线程
	- 线程是进程中的一个实体，是被系统独立调度和分派的基本单位；
	- 同一个进程中有多个现成；
	- 线程共享进程的资源；
	- 计算机处理器一个核心只能同时执行一个线程；
- 线程交互
	- 互斥：资源竞争，等待；
	- 同步：线程间的互相配合；


## 2. 线程
### 2.1 类与接口
- `Runnable`：线程接口；
- `Thread`：线程类；

### 2.2 线程常用方法
- `Thread()`：构造方法，创建线程，可以指定`Runnable`或名称；
- `start()`：启动线程；
- `sleep()`：静态方法，休眠当前线程，可以指定休眠时间；
- `join()`：使其他线程等待当前线程终止；需要控制结束程序的地方非常有用；
- `yield()`：静态方法，当前线程释放处理资源，线程需要重新竞争；
- `currentThread()`：返回当前运行的线程引用；

### 2.3 停止线程
- `stop()`：已经过时，错误的方法，线程戛然而止，无法确定完成了那些工作；线程被强制关闭；
- `interrupt()`：并不能正确的停止线程，会设置中断状态，在执行`join`,`sleep`方法进入阻塞时会抛出`InterruptedException`，可以使用`isIntereupted()`方法获取中断状态；
- **正确的方法**：使用`volatile`的`boolean`退出标识，确保代码完整执行，不会突然中止；

### 2.4 线程同步
#### 2.4.1 基本概念
- 争用条件： 当多个线程同时共享访问统一数据（内存区域）时，每个线程都尝试操作该数据，从而导致数据被破坏，这种现象称为争用条件；
- 互斥：在同一时间能够有一个线程操作数据区；
- 同步： 线程间的通信机制，可以用某种方式告诉其他线程自己的操作已经结束；

#### 2.4.2 同步锁
- `synchronized(lock)`块：传入一个锁对象，同时只有一个线程可以进入同步块中；
- `synchronized`方法：将一个方法本身设置为锁对象，同时只有一个线程可以进入该方法；

#### 2.4.3 同步方法
- `wait()`：将当前线程放在该锁对象的`wait set`中；在资源不足时可以调用该方法进行等待；
- `notifyAll()`：唤醒所有在该锁对象上等待的线程；
- `notify()`：唤醒一个在该锁对象上等待的线程，随机的一条线程，无法具体控制；

> **临界区(Critical Section)**：当有线程进入临界区段时，其他线程或是进程必须等待，以确保这些共用资源是被互斥获得使用；
> **Wait Set**：线程等待区域，调用`wait()`方法时进入，`notify()`或`notifyAll()`被调用时释放；

#### 2.4.4 基本范例
``` java
private final Object lockObject = new Object();
public void func(){
	//同步块加锁
	synchronized (lockObject){
		//判断资源条件，防止重复加锁浪费资源
	    while(!condition){
	        //当前线程等待，让出CPU资源
	        lockObject.wait();
	    }
	}
}
//唤醒线程
lockObject.notifyAll();
```

## 3. 继续学习
- JMM, happens-before
- Locks & Condition
- 线程安全，原子性、可见性、atomic、DeadLocks、可见性编程
- 多线程交互模型： Future, Worker Thread, 
- 并发编程工具 concurrent , ExcutorService， Callable, Future, BlockingQueue
- core java；