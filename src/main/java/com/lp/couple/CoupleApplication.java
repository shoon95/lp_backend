package com.lp.couple;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoupleApplication {

    public static void main(String[] args) {
        // .env 파일에서 환경 변수 로드
        String activeProfile = System.getenv("SPRING_PROFILES_ACTIVE");

        // 값이 없으면 기본값으로 'dev' 프로파일을 사용합니다.
        if (activeProfile == null || activeProfile.isEmpty()) {
            activeProfile = "dev";
            System.setProperty("SPRING_PROFILES_ACTIVE", activeProfile);

        }

        // 해당 프로파일에 맞는 .env 파일 로드
        Dotenv dotenv;
        if ("prod".equals(activeProfile)) {
            dotenv = Dotenv.configure().filename(".env.prod").load();
        } else {
            dotenv = Dotenv.configure().filename(".env.dev").load();
        }

        // .env 파일에서 읽은 환경 변수를 시스템 속성으로 설정
        dotenv.entries().forEach(entry -> {
            System.out.println("entry = " + entry);
            if (System.getenv(entry.getKey()) == null) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });

        SpringApplication.run(CoupleApplication.class, args);
    }
}