package fun.hara.demo.service.impl;

import fun.hara.demo.service.EchoService;
public class EchoServiceImpl implements EchoService {
    public String echo(String msg) {
        return msg;
    }
}
