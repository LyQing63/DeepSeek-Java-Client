package io.github.lyqing63.deepseeksdk.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static io.github.lyqing63.deepseeksdk.constant.ClientConstant.*;

@Data
public class DeepSeekClient {

    private String apiKey;

    private String model;

    private Boolean stream;

    /**
     * 用于请求发起缓存，触发上下文硬盘
     */
    private JSONArray messages;

    public DeepSeekClient(String apiKey) {
        this.apiKey = apiKey;
        this.model = MODEL;
        this.stream = false;
    }

    public String newAsk(String question) {
        messages = new JSONArray();
        String answer = ask(question);

        return answer;
    }


    public String askForward(String question) {
        addMessage(question, USER_ROLE);
        String answer = ask(question);

        return answer;
    }

    /**
     * 设置初始角色
     */
    public void setSystem(String prompt) {
        messages = new JSONArray();
        addMessage(prompt, SYSTEM_ROLE);
    }

    private JSONObject getBody(String question) {
        JSONObject body = new JSONObject();

        Map<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("model", this.model);

        addMessage(question, USER_ROLE);

        bodyMap.put("messages", this.messages);
        bodyMap.put("stream", this.stream);

        body.putAll(bodyMap);

        return body;
    }

    private void addMessage(String question, String role) {
        JSONObject message = new JSONObject();
        message.putOnce("role", role);
        message.putOnce("content", question);
        this.messages.put(message);
    }

    private String ask(String question) {
        JSONObject body = getBody(question);
        String responseBody = HttpRequest.post(BASE_URL)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.apiKey)
                .body(body.toString())
                .execute()
                .body();
        if (!JSONUtil.isTypeJSON(responseBody)) {
            throw new RuntimeException(responseBody);
        }

        JSONObject responseBodyJSON = JSONUtil.parseObj(responseBody);

        String answer = getAnswer(responseBodyJSON);

        // 为下轮对话做准备
        addMessage(answer, ASSISTANT_ROLE);

        return answer;
    }

    private String getAnswer(JSONObject response) {
        JSONArray choices = response.getJSONArray("choices");
        JSONObject message = choices.getJSONObject(0).getJSONObject("message");
        return message.get("content").toString();
    }
}
