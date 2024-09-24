package io.github.lyqing63.deepseeksdk.domain.response;

import lombok.Data;

@Data
public class DeepSeekChatResponse {

    /**
     * 该对话的唯一标识符。
     */
    private String id;

    /**
     * 模型生成的 completion 的选择列表。
     */
    private Object[] choices;

    /**
     * 创建聊天完成时的 Unix 时间戳（以秒为单位）。
     */
    private Integer created;

    /**
     * 生成该 completion 的模型名。
     */
    private String model;

    /**
     * This fingerprint represents the backend configuration that the model runs with.
     */
    private String systemFingerprint;

    /**
     * Possible values: [chat.completion]
     * 对象的类型, 其值为 chat.completion。
     */
    private String object;

    /**
     * 该对话补全请求的用量信息。
     */
    private Object usage;

}
