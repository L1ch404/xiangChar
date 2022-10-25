package net.lich.xiangchar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class XiangCharApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiangCharApplication.class, args);
    }

}
