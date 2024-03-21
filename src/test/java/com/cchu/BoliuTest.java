package com.cchu;

import com.alibaba.fastjson.JSONObject;
import com.boliu.sdk.BLClient;
import com.boliu.sdk.BLSDK;
import com.boliu.sdk.exception.BLException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class BoliuTest {
    @Test
    public void test(){
        try {
            BLClient init = BLSDK.init("127.0.0.1", 7015, "127.0.0.1", 7007, "127.0.0.1", 7006);
            log.info("-----init{}",init);
//注册用户
//            String content = "显示信息（注册用户（“李三1”，“1234abcd”，“13800138002”，“440682200001010102”，“广东佛山1”，“系统管理员”））";
//            JSONObject jsonObject = init.executeCommand(content);
//                        log.info("======注册用户{}",jsonObject);

//            用户登录
//            String content0 = "显示信息(登录系统(\"系统管理员\"，\"1234abcd\"))\n" +
//                    "显示信息（激活用户（“李三”））";
//            JSONObject jsonObject0 = init.executeCommand(content0);
//            log.info("======激活和登录{}",jsonObject0);
//            String content1 = "显示信息（信息签名（测试上联1））";
//            JSONObject jsonObject1 = init.executeCommand(content1);
//            log.info("======信息签名{}",jsonObject1);
            String content2 = "显示信息(登录系统(\"李三\"，\"1234abcd\"))\n" +
                    "准备数据（\"10330989253392864233428245339282243342804633028965337287563372854633598674337989453379824531\"）\n" +
                    "显示信息（数据上链（））";
            JSONObject jsonObject2 = init.executeCommand(content2);
            log.info("======数据上链{}",jsonObject2);

        } catch (BLException e) {
            throw new RuntimeException(e);
        }

    }
}
//22:10:42.699 [main] DEBUG com.boliu.sdk.BLSDK - [init,25] - 正在连接预言合约服务器：127.0.0.1:7015
//        22:10:42.760 [WebSocketConnectReadThread-22] DEBUG c.boliu.sdk.BLClient - [onOpen,114] - 握手成功，已通过[/127.0.0.1:59559]连接至数据库
//        22:10:42.761 [main] DEBUG com.boliu.sdk.BLSDK - [init,44] - 正在设置上链服务器：127.0.0.1:7007
//        22:10:42.761 [main] DEBUG c.boliu.sdk.BLClient - [executeCommand,175] - [BOLIU] COMMAND:显示信息(上链服务器设置("广东辰宜","127.0.0.1","7007"))
//        22:10:42.784 [main] DEBUG c.boliu.sdk.BLClient - [executeCommand,197] - [BOLIU] COMMAND:显示信息(上链服务器设置("广东辰宜","127.0.0.1","7007")) ==> RESULT:{"上链服务器设置": [ {"上链服务器设置":"上链服务器设置成功","上链服务器类型":"主链服务器"} ] } ==> TIME(ms):23
//        22:10:42.900 [main] DEBUG com.boliu.sdk.BLSDK - [init,69] - 正在设置智能合约服务器：127.0.0.1:7006
//        22:10:42.900 [main] DEBUG c.boliu.sdk.BLClient - [executeCommand,175] - [BOLIU] COMMAND:显示信息(合约服务器设置("智能合约服务","127.0.0.1","7006"))
//        22:10:42.908 [main] DEBUG c.boliu.sdk.BLClient - [executeCommand,197] - [BOLIU] COMMAND:显示信息(合约服务器设置("智能合约服务","127.0.0.1","7006")) ==> RESULT:{"合约服务器设置": [ {"合约服务器设置":"合约服务器设置成功"} ] } ==> TIME(ms):8
//        22:10:42.909 [main] INFO  com.cchu.BoliuTest - [test,16] - -----initcom.boliu.sdk.BLClient@72d6b3ba
//        22:10:42.909 [main] DEBUG c.boliu.sdk.BLClient - [executeCommand,175] - [BOLIU] COMMAND:显示信息(登录系统("李三"，"1234abcd"))
//        准备数据（"10330989253392864233428245339282243342804633028965337287563372854633598674337989453379824531"）
//        显示信息（数据上链（））
//        22:10:42.967 [main] DEBUG c.boliu.sdk.BLClient - [executeCommand,197] - [BOLIU] COMMAND:显示信息(登录系统("李三"，"1234abcd"))
//        准备数据（"10330989253392864233428245339282243342804633028965337287563372854633598674337989453379824531"）
//        显示信息（数据上链（）） ==> RESULT:{"登录系统": [ {"登录系统":"区块链系统登录成功！"} ] ,"数据上链": [ {"数据上链":"数据上链成功！","上链用户":"李三","哈希值":"202403212210420000000100004666355455646420506224534646623043353342536564264355226546056204","区块高度":"0","业务名称":"cy_chain","数据":"10330989253392864233428245339282243342804633028965337287563372854633598674337989453379824531","公开数据":"20240321221042000000010000.","数据ID":"","数据KEY":"","关联动作":"","数据URL":"","上链时间":"2024-03-21 22:10:42"} ] } ==> TIME(ms):57
//        22:10:42.969 [main] INFO  com.cchu.BoliuTest - [test,34] - ======数据上链{"登录系统":[{"登录系统":"区块链系统登录成功！"}],"数据上链":[{"数据上链":"数据上链成功！","上链用户":"李三","哈希值":"202403212210420000000100004666355455646420506224534646623043353342536564264355226546056204","区块高度":"0","业务名称":"cy_chain","数据":"10330989253392864233428245339282243342804633028965337287563372854633598674337989453379824531","公开数据":"20240321221042000000010000.","数据ID":"","数据KEY":"","关联动作":"","数据URL":"","上链时间":"2024-03-21 22:10:42"}]}