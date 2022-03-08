package io.hongting;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * @author hongting
 * @create 2021 11 03 12:51 PM
 */


@EnableAsync
@ServletComponentScan
@SpringBootApplication
public class BlogMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogMainApplication.class, args);
    }
}
