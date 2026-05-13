package org.example.umc10th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class Umc10thApplication {

    public static void main(String[] args) {
        SpringApplication.run(Umc10thApplication.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("password123"));
    }

}
