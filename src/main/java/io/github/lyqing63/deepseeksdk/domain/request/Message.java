package io.github.lyqing63.deepseeksdk.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {

    /**
     * 询问内容
     */
    private String content;

    /**
     * 询问角色
     * 可能的值：
     * 1. system
     * 2. user
     * 3. assistant
     */
    private String role;

}
