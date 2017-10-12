package com.binge.smart.rpc.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * @author binge
 * @datetime 2017年10月12日
 * @version
 * @encoding UTF8
 * @Description 服务端的服务提供
 */

public class ProviderReflect {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 服务发布
     * 
     * @param service
     * @param port
     * @throws Exception
     * @throws exception
     */
    public static void provider(final Object service, int port) throws Exception {
        @SuppressWarnings("resource")
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            final Socket socket = serverSocket.accept();
            executorService.execute(new Runnable() {

                public void run() {
                    try {
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            try {
                                // 方法名称
                                String methodName = input.readUTF();
                                // 方法参数
                                Object[] arguments = (Object[]) input.readObject();

                                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                                try {
                                    // 方法引用
                                    Object result = MethodUtils.invokeExactMethod(service, methodName, arguments);
                                    output.writeObject(result);
                                } catch (Throwable t) {
                                    output.writeObject(t);
                                } finally {
                                    output.close();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                input.close();
                            }
                        } finally {
                            socket.close();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
