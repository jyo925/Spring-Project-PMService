package com.project.bit;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.bit.foo.controller.MyPageController;

@SpringBootApplication
public class BitApplication {

    public static void main(String[] args) {
		SpringApplication.run(BitApplication.class, args);
        
    }


}
