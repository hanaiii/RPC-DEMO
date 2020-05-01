package fun.hara.demo.rpc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * RPC请求
 * @author hanaii
 */
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 8896424670801602879L;
    /**
     * 服务名，即服务类全限定名
     */
    private String serviceName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 方法参数
     */
    private Object[] args;
}
