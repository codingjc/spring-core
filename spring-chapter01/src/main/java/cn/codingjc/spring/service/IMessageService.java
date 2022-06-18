package cn.codingjc.spring.service;

/**
 * @author codingjc
 * @date 2022-06-18 22:58
 */
public interface IMessageService {

    /**
     * 处理消息类
     * @param msg 接受到的信息
     * @return 处理后的消息
     */
    String echo(String msg);
}
