package com.binge.smart.rpc.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author binge
 * @datetime 2017年10月12日
 * @version
 * @encoding UTF8
 * @Description 客户端消费代理类
 */

public class ConsumerProxy {

    /**
     * 获取RPC调用代理类
     * 
     * @param cls
     * @param host
     * @param port
     * @return
     * @throws exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class<T> cls, final String host, final int port) {
        return (T) Enhancer.create(cls, new MethodInterceptor() {
            public Object intercept(Object targetObject, Method targetMethod, Object[] methodArgs,
                    MethodProxy methodProxy) throws Throwable {
                Socket socket = new Socket(host, port);
                try {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    try {
                        output.writeUTF(targetMethod.getName());
                        output.writeObject(methodArgs);
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            Object result = input.readObject();
                            if (result instanceof Throwable) {
                                throw (Throwable) result;
                            }
                            return result;
                        } finally {
                            input.close();
                        }
                    } finally {
                        output.close();
                    }
                } finally {
                    socket.close();
                }
            }
        });
    }

}
