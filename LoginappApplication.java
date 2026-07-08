package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginappApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginappApplication.class, args);
        System.out.println("==========================================");
        System.out.println("✅ LOGIN APP IS RUNNING!");
        System.out.println("🔐 Login: http://localhost:8090/login");
        System.out.println("📝 Register: http://localhost:8090/register");
        System.out.println("==========================================");
    }
}