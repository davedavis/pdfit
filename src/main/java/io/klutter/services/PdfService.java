package io.klutter.services;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.params.Param;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@RequestMapping("/api/v1")
public class PdfService {



    @RequestMapping("/pdf")

    public String index(){

        String url = "https://realpython.com/python-sockets/";
        Document doc = null;
        try {
            doc = Jsoup.connect("https://realpython.com/python-sockets/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pdf pdf = new Pdf();

        // pdf.addPageFromString(doc.toString());
        pdf.addPageFromUrl("https://realpython.com/python-sockets/");

        // Add a Table of Contents
        pdf.addToc();
        pdf.addParam(new Param("--disable-javascript"));

        // Add styling for Table of Contents
        //        pdf.addTocParam(new Param("--xsl-style-sheet", "my_toc.xsl"));

        // Save the PDF
        try {
            pdf.saveAs("output.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Hello from another mapping";
    }
}

