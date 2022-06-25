package cn.codingjc.spring.service.impl;

import cn.codingjc.spring.service.IMessageService;

/**
 * @author codingjc
 * @date 2022-06-18 22:59
 */
public class MessageServiceImpl implements IMessageService {

    @Override
    public String echo(String msg) {
        return "【ECHO】:" + msg;
    }
}
