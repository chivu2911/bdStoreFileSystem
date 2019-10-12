package com.example.apptransaction;

import com.example.apptransaction.dao.IFileDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppTransactionApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(AppTransactionApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(AppTransactionApplication.class, args);
    }

    @Autowired
    private IFileDao dao;

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner", args);

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
