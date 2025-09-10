package cn.softfocus.xkzy.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${xkzy.info.base-package}
@SpringBootApplication(scanBasePackages = {"cn.softfocus.xkzy.server", "cn.softfocus.xkzy.module"})
public class XkzyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XkzyServerApplication.class, args);
    }

}
