package ru.vkr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.vkr.service.WorkScheduler;

@EnableScheduling
@SpringBootApplication
public class ApplicationMain {

    public static void main(String[] args) {
       ConfigurableApplicationContext ctx = SpringApplication.run(ApplicationMain.class, args);
       WorkScheduler workScheduler = ctx.getBean(WorkScheduler.class);
       workScheduler.schedule();
    }
}
