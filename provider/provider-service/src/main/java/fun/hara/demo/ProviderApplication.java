package fun.hara.demo;

import fun.hara.demo.rpc.RpcProxyServer;
import fun.hara.demo.service.impl.EchoServiceImpl;

/**
 * 提供者 Application
 * @author hanaii
 */
public class ProviderApplication {
    public static void main(String[] args) {
        System.out.println("Provider Application Running");
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.publisher(new EchoServiceImpl(), 80);
    }
}
