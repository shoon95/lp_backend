package com.lp.couple;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoupleApplication {

    public static void main(String[] args) {
        // .env 파일에서 환경 변수 로드
        Dotenv dotenv = Dotenv.configure()
                .directory("./")  // .env 파일의 경로를 명시적으로 설정
                .ignoreIfMissing()
                .load();

        dotenv.entries().forEach(entry -> {
            // 시스템 환경 변수로 설정되어 있지 않은 경우에만 시스템 속성으로 설정
            if (System.getenv(entry.getKey()) == null) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });

        SpringApplication.run(CoupleApplication.class, args);
    }
}