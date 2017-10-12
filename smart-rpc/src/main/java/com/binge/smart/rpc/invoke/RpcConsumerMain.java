package com.binge.smart.rpc.invoke;

import com.binge.smart.rpc.proxy.ConsumerProxy;
import com.binge.smart.rpc.service.HelloService;

/**
 * @author binge
 * @datetime 2017年10月12日
 * @version
 * @encoding UTF8
 * @Description
 */

public class RpcConsumerMain {
    public static void main(String[] args) throws Exception {
        HelloService service = ConsumerProxy.getProxy(HelloService.class, "127.0.0.1", 8083);
        for (int i = 0; i < 1000; i++) {
            String hello = service.sayHello("binge_" + i);
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }
}
