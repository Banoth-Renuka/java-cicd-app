package com.example;

import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
        System.out.println("--- Java CI/CD Application ---");
        System.out.println("Hello from Jenkins Pipeline!");
        System.out.println("Build Time: " + LocalDateTime.now());
        System.out.println("------------------------------");
    }

    public static String getGreeting() {
        return "App is running!";
    }
}
