package com.example.apptransaction;

import com.example.apptransaction.dao.IFileDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AppTransactionApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(AppTransactionApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AppTransactionApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    @Autowired
    private IFileDao dao;

    @Override
    public void run(String... args) {

        if(args.length > 1){
            switch (args[1]){
                case "add":
                    dao.add(args[0], args[2]);
                break;
                case "list":
                    dao.list(args[0]);
                    break;
                case "sum":
                    dao.sum(args[0]);
                    break;
                    default:
                        dao.show(args[0], args[1]);
                        break;
            }

        }

    }

}
