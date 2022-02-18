package io.pdfit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Add the scanBasePackages parameter to the annotation as I added my services in
//separate packages so, they need to be configured on application start.
@SpringBootApplication(scanBasePackages = {"io.pdfit.declutterservice", "io.pdfit.pdfservice"} )
public class PdfitApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdfitApplication.class, args);
        System.out.println("Application Running: http://localhost:8080");
    }

}
