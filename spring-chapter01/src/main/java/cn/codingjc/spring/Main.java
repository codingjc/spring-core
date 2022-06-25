package cn.codingjc.spring;

import cn.codingjc.spring.service.IMessageService;
import cn.codingjc.spring.service.impl.MessageServiceImpl;
import cn.codingjc.spring.util.ObjectFactory;

/**
 * 程序主入口
 * @author codingjc
 * @date 2022-06-18 23:01
 */
public class Main {

    /**
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        method1();
        method2();
        method3();
    }

    /**
     * 1、对象实例化的本质操作
     *
     * 当前设计存在哪些问题？
     *   1、如果当前程序运行在多线程的环境中，不同的线程都需要维护自己对象的状态，如果出现线程数量暴增的情况，最终导致JVM频繁GC，
     * 导致程序出现严重性能问题。
     *   2、业务开发中，业务接口要处理的VO类型可能不同，但是业务对象的处理都是相同的，此时想要实现业务对象的重用该怎么做？
     */
    public static void method1(){
        IMessageService messageService = new MessageServiceImpl();
        System.out.println(messageService.echo("Hello1"));
    }

    /**
     * 2、工厂设计模式
     *
     * 当前设计存在哪些问题？
     *   1、当前设计已经解决了关键字new带来了耦合问题，但出现了应用的程序部分需要编写完整的类名称，如果此时需要更换实现类，就需要更改源代码。
     * 出现了配置结构上的耦合问题。
     *
     */
    public static void method2(){
        String className = "cn.codingjc.spring.service.impl.MessageServiceImpl";
        IMessageService messageService = ObjectFactory.getInstance1(className, IMessageService.class);
        System.out.println(messageService.echo("Hello2"));
    }

    /**
     * 3、配置文件管理使用类
     *
     * 通过读取配置文件，将对象实例化先加载在缓冲中，通过工厂方法取
     */
    public static void method3(){
        IMessageService messageService = ObjectFactory.getInstance2("messageService", IMessageService.class);
        System.out.println(messageService.echo("Hello3"));
    }
}
