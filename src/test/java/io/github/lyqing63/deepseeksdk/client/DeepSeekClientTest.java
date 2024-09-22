package io.github.lyqing63.deepseeksdk.client;

import org.junit.jupiter.api.Test;

class DeepSeekClientTest {

    private final String API_KEY = "";

    private final DeepSeekClient client = new DeepSeekClient(API_KEY);

    @Test
    void newAskTest() {
        String res = client.newAsk("你好");
        System.out.println(res);
    }

    @Test
    void askForwardTest() {
        String res1 = client.newAsk("请问世界最高山是什么山");
        System.out.println(res1);
        String res2 = client.askForward("第二呢");
        System.out.println(res2);
    }

    @Test
    void askWithAssistant() {
        client.setSystem("你是一个资深的后端工程师");
        String res = client.askForward("Redis是什么");
        System.out.println(res);
    }

}
