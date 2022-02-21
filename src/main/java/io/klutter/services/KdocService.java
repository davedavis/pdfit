package io.klutter.services;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.klutter.data.KdocRepository;
import io.klutter.models.Kdoc;
import io.whelk.flesch.kincaid.ReadabilityCalculator;
import net.dankito.readability4j.Readability4J;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class KdocService {

    private final KdocRepository kdocRepository;

    public KdocService(KdocRepository kdocRepository) {
        this.kdocRepository = kdocRepository;
    }

    public List<Kdoc> getAllKdocs(){
        return kdocRepository.findAll();
    }

//    @RequestMapping("/documents")
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


}

