package io.klutter.services;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.klutter.data.KdocRepository;
import io.klutter.models.Kdoc;
import io.whelk.flesch.kincaid.ReadabilityCalculator;
import net.dankito.readability4j.Readability4J;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class DeclutterService {

    // Set up the Kdoc repository to store decluttered Kdocs
    @Autowired
    KdocRepository kdocRepository;



    @PostMapping(value = "/declutter", produces = {"application/json"})
        // public String declutter(@ModelAttribute Kdoc kdoc, Model model) {
        ResponseEntity<Kdoc> declutter(@ModelAttribute Kdoc kdoc, Model model) {
//        model.addAttribute("kdoc", kdoc);
        System.out.println(kdoc);
        String url = kdoc.getUrl();

        if (kdocRepository.existsKdocByUrl(url)){
            System.out.println("URL Exists in the DB!");
            return new ResponseEntity<>(kdoc, HttpStatus.OK);
            }

        else {
            System.out.println("This is a new URL");

            ChromeOptions options = new ChromeOptions();options.addArguments("--headless");

            // Using Webdriver
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver(options);

            // ToDo: Receive URL from frontend.
            driver.get(url);

            // Get the raw HTML source.
            String html = driver.getPageSource();

            // Parse with Jsoup, so we can work with it.;
            Document doc = Jsoup.parse(html);

            // ToDo: Do a bit of sanitization on the HTML before passing to the PDF service.
            String safe = Jsoup.clean(doc.html(), Safelist.basic());

            // Process with the readability4j mozilla readability.js wrapper.
            Readability4J readability4J = new Readability4J(url, doc);
            net.dankito.readability4j.Article article = readability4J.parse();

            // returns extracted content in a <div> element
            String extractedContentHtml = article.getContent();
            // to get content wrapped in <html> tags and encoding set to UTF-8, see chapter 'Output encoding'
            String extractedContentHtmlWithUtf8Encoding = article.getContentWithUtf8Encoding();
            String extractedContentPlainText = article.getTextContent();
            String title = article.getTitle();
            String byline = article.getByline();
            String excerpt = article.getExcerpt();


            // Get the reading ease score.
            double ease = ReadabilityCalculator.calculateReadingEase(extractedContentPlainText);

            // Get the grade level score.
            double grade = ReadabilityCalculator.calculateGradeLevel(extractedContentPlainText);

            kdoc.setTitle(title);
            kdoc.setExcerpt(excerpt);
            kdoc.setByline(byline);
            kdoc.setEase(ease);
            kdoc.setGrade(grade);
            kdoc.setContent(extractedContentHtml);
            kdocRepository.save(kdoc);

            // ToDo: Add user, title, excerpt, byline, content, ease, grade and tag array to model.

            return new ResponseEntity<>(kdoc, HttpStatus.OK);


            }

    }


}

//    // Working
//    @RequestMapping("/declutter")
//    public String index()  {
//
//        String url = "https://realpython.com/python-sockets/";
//
//        // Selenium. Using Selenium because jsoup doesn't handle JS and lazy loading.
//        // System.setProperty("webdriver.chrome.driver", "/home/dave/chromedriver");
//        ChromeOptions options = new ChromeOptions();options.addArguments("--headless");
//        //WebDriver driver = new ChromeDriver(options);
//
//        // Using Webdriver
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver(options);
//
//        // ToDo: Receive URL from frontend.
//        driver.get(url);
//
//        // Get the raw HTML source.
//        String html = driver.getPageSource();
//
//        // Parse with Jsoup, so we can work with it.;
//        Document doc = Jsoup.parse(html);
//
//        // ToDo: Do a bit of sanitization on the HTML before passing to the PDF service.
//        String safe = Jsoup.clean(doc.html(), Safelist.basic());
//
//        // Process with the readability4j mozilla readability.js wrapper.
//        Readability4J readability4J = new Readability4J(url, doc);
//        net.dankito.readability4j.Article article = readability4J.parse();
//
//        // returns extracted content in a <div> element
//        String extractedContentHtml = article.getContent();
//        // to get content wrapped in <html> tags and encoding set to UTF-8, see chapter 'Output encoding'
//        String extractedContentHtmlWithUtf8Encoding = article.getContentWithUtf8Encoding();
//        String extractedContentPlainText = article.getTextContent();
//        String title = article.getTitle();
//        String byline = article.getByline();
//        String excerpt = article.getExcerpt();
//
//
//        // Get the reading ease score.
//        double ease = ReadabilityCalculator.calculateReadingEase(extractedContentPlainText);
//
//        // Get the grade level score.
//        double grade = ReadabilityCalculator.calculateGradeLevel(extractedContentPlainText);
//
//        // Check it's working
//        System.out.println(ease + " " + grade);
//
//        // Testing the sizing of the document for Azure
////        System.out.println(">>>>>>>>>>>>>>>>>>>>>>   " + extractedContentPlainText.length());
////        System.out.println(">>>>>>>>>>>>>>>>>>>>>>   " + WordUtils.wrap(extractedContentPlainText, 40));
//
//        // ToDo: Add user, title, excerpt, byline, content, ease, grade and tag array to model.
//
//        // Return the clean HTML
//        return extractedContentHtml;
//    }

