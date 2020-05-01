package fun.hara.demo.rpc;

import java.lang.reflect.Proxy;

/**
 * RPC 代理客户端
 * @author hanaii
 */
public class RpcProxyClient {
    public <T>T clientPoxy(Class<T> serviceClass, String host, int port){
        return  (T) Proxy.newProxyInstance(
                    serviceClass.getClassLoader(),
                    new Class<?>[]{serviceClass},
                    new RpcInvocationHandler(host, port)
                );
    }
}
