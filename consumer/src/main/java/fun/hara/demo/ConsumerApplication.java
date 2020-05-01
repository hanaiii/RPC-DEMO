package fun.hara.demo;

import fun.hara.demo.rpc.RpcProxyClient;
import fun.hara.demo.service.EchoService;

/**
 * 消费者 Application
 * @author hanaii
 */
public class ConsumerApplication {
    public static void main(String[] args) {
        EchoService echoService = new RpcProxyClient().clientPoxy(EchoService.class, "localhost", 80);
        String result = echoService.echo("hello");
        System.out.println(result);
    }
}
