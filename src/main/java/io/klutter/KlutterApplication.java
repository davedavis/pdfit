package io.klutter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Add the scanBasePackages parameter to the annotation as I added my services in
//separate packages so, they need to be configured on application start.
@SpringBootApplication(scanBasePackages = {"io.klutter.declutterservice", "io.klutter.pdfservice"} )
public class KlutterApplication {

    public static void main(String[] args) {
        SpringApplication.run(KlutterApplication.class, args);
        System.out.println("Application Running: http://localhost:8080");
    }

}
