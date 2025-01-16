package org.example.onlinecourse;

import org.example.onlinecourse.utils.Messages;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineCourseApplication.class, args);
        System.out.println("Messages.IMAGE_PATH = " + Messages.IMAGE_PATH);
        System.out.println("args = " + Messages.BASE_URL);
    }

}
