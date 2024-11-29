package com.example.File_Hidding_Project;

import com.example.File_Hidding_Project.views.Welcome;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileHiddingProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileHiddingProjectApplication.class, args);
		System.out.println("Started...");
        Welcome w = new Welcome();
        do {
            w.welcomeScreen();
        } while (true);
	}

}
