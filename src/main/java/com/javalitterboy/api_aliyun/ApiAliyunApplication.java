package com.javalitterboy.api_aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author 14183
 */
@SpringBootApplication
public class ApiAliyunApplication {

    @Value("${accessKeyId}")
    private String accessKeyId;
    @Value("${accessSecret}")
    private String accessSecret;
    @Value("${location}")
    private String location;

    public static void main(String[] args) {
        SpringApplication.run(ApiAliyunApplication.class, args);
    }

    @Bean
    public IAcsClient iAcsClient(){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        return new DefaultAcsClient(profile);
    }
}
