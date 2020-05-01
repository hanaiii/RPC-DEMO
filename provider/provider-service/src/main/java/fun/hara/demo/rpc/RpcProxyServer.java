package fun.hara.demo.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RPC 代理服务器
 * @author hanaii
 */
public class RpcProxyServer {
    /**
     * 可缓存线程池
     */
    ExecutorService threadPool = Executors.newCachedThreadPool();

    /**
     * 服务注册
     * @param service
     * @param port
     */
    public void publisher(Object service, int port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                threadPool.execute(new RpcRequestHandler(socket, service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
