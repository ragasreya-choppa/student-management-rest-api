package com.example.springdemo.practice;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64UrlDemo {
    public static void main(String[]args){

        String header = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";

        String payload = "{\"sub\":\"sreya\",\"role\":\"USER\"}";

        String encodedHeader = Base64.getUrlEncoder().withoutPadding().encodeToString(header.getBytes(StandardCharsets.UTF_8));

        String encodedPayload = Base64.getEncoder().withoutPadding().encodeToString(payload.getBytes(StandardCharsets.UTF_8));

        System.out.println("Original Header: ");
        System.out.println(header);

        System.out.println("\nEncoded Header: ");
        System.out.println(encodedHeader);

        System.out.println("\nOriginal Payload: ");
        System.out.println(payload);

        System.out.println("\nEncoded Payload: ");
        System.out.println(encodedPayload);



    }
}
