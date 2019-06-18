package edu.mum.cs.cs425.lakedarlingresortapp;

//import edu.mum.cs.cs425.lakedarlingresortapp.config.WebConfig;
//import edu.mum.cs.cs425.lakedarlingresortapp.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class LakedarlingresortappApplication {


    public static void main(String[] args) {
        SpringApplication.run(LakedarlingresortappApplication.class, args);
    }


}
