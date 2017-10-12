package com.binge.smart.rpc.invoke;

import com.binge.smart.rpc.proxy.ProviderReflect;
import com.binge.smart.rpc.service.HelloService;
import com.binge.smart.rpc.service.HelloServiceImpl;

/**
 * @author binge
 * @datetime 2017年10月12日
 * @version
 * @encoding UTF8
 * @Description
 */

public class RpcProviderMain {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        ProviderReflect.provider(service, 8083);
    }
}
