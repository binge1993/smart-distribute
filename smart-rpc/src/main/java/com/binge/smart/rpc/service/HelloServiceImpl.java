package com.binge.smart.rpc.service;

/**
 * @author binge
 * @datetime 2017年10月12日
 * @version
 * @encoding UTF8
 * @Description 服务端实现类
 */

public class HelloServiceImpl implements HelloService {

    public String sayHello(String content) {
        return "hello," + content;
    }

}
