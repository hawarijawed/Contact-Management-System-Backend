package com.yakria.cms;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class ContanctManagementApplication {
//	@Value("${spring.data.mongodb.uri}")
////	private String mongoUri;
//@Value("${spring.application.name}")
//private String appName;


	public static void main(String[] args) {
//		Dotenv dotenv = Dotenv.load();
//		System.setProperty("MONGODB_URI", dotenv.get("MONGO_DB_URI"));

		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kathmandu"));
		SpringApplication.run(ContanctManagementApplication.class, args);
        System.out.println("Hello there");
	}

//	@PostConstruct
//	public void printMongoUri() {
//		System.out.println("MongoDB URI: " + appName);
//	}

}
