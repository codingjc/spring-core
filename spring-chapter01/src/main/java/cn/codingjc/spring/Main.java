package cn.codingjc.spring;

import cn.codingjc.spring.service.IMessageService;
import cn.codingjc.spring.service.impl.MessageServiceImpl;

/**
 * 程序主入口
 * @author codingjc
 * @date 2022-06-18 23:01
 */
public class Main {

    public static void main(String[] args) {
        IMessageService messageService = new MessageServiceImpl();
        System.out.println(messageService.echo("Hello"));
    }
}
