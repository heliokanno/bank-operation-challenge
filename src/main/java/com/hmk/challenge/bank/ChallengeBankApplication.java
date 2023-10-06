package com.hmk.challenge.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@EnableJpaRepositories(basePackages = "com.hmk.challenge.bank.dataprovider.repository")
@SpringBootApplication
public class ChallengeBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChallengeBankApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
