package fun.hara.demo.rpc;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * RPC 请求处理
 * @author hanaii
 */
@AllArgsConstructor
public class RpcRequestHandler implements Runnable {
    private Socket socket;
    private Object service;

    public void run() {
        try (
            ObjectInputStream inputStream =
                    new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream =
                    new ObjectOutputStream(socket.getOutputStream());
        ) {
            // 通过输入流获取请求数据
            RpcRequest request = (RpcRequest) inputStream.readObject();
            // 调用服务
            Object invoke = invoke(request);
            // 返回结果
            outputStream.writeObject(invoke);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据RPC请求调用对应的服务方法
     * @param request
     * @return
     */
    private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 找到对应类
        Class<?> clz = Class.forName(request.getServiceName());

        // 找到对应方法
        Object[] args = request.getArgs();
        Class<?>[] types = new Class
                [args == null ? 0 : args.length];
        for (int i = 0; i < types.length; i++) {
            types[i] = args[i].getClass();
        }
        Method method = clz.getMethod(request.getMethodName(), types);
        // 调用
        return method.invoke(service, args);
    }
}
