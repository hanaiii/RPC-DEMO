package fun.hara.demo.rpc;

import lombok.AllArgsConstructor;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * RPC 调用处理
 * @author hanaii
 */
@AllArgsConstructor
public class RpcInvocationHandler implements InvocationHandler {
    /**
     * 地址
     */
    private String host;
    /**
     * 端口
     */
    private int port;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest(
                method.getDeclaringClass().getName(),
                method.getName(), args);
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(host, port));
        try(
                ObjectOutputStream outputStream =
                        new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputStream =
                        new ObjectInputStream(socket.getInputStream());
        ){
            outputStream.writeObject(rpcRequest);
            outputStream.flush();
            Object object = inputStream.readObject();
            return object;
        }
    }
}
