package io.github.lyqing63.deepseeksdk;

import io.github.lyqing63.deepseeksdk.client.DeepSeekClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("deepseek.client")
@Data
@ComponentScan
public class DeepSeekClientConfig {

    private String apiKey;

    @Bean
    public DeepSeekClient deepSeekClient() {
        return new DeepSeekClient(apiKey);
    }

}
