import io.github.bonigarcia.wdm.WebDriverManager;
import io.klutter.data.KdocRepository;
import io.klutter.models.Kdoc;
import io.klutter.services.KdocService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.whelk.flesch.kincaid.ReadabilityCalculator;
import net.dankito.readability4j.Readability4J;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;


import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BakWebController {

    // Set up the repository reference.
    @Autowired
    KdocRepository kdocRepository;


//        @RequestMapping(value = "/", method = RequestMethod.POST)
//        ResponseEntity<Kdoc> generateKdocFromUrl(@RequestBody Kdoc submittedKdoc) {
//        System.out.println(submittedKdoc.toString());
//        // Decode the URL encoded string if it's encoded.
//        String decoded = java.net.URLDecoder.decode(submittedKdoc.getUrl(), StandardCharsets.UTF_8);
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> Standard  " + submittedKdoc );
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> Decoded " + decoded );
//
//
//        Kdoc savedKdoc = kdocRepository.save(submittedKdoc);
//        // ToDo: Change to savedkDoc
//        return new ResponseEntity<Kdoc>(submittedKdoc, HttpStatus.OK);
//        }

    @RequestMapping(value = "/declutter2", method = RequestMethod.GET)
    public String index()  {
        String url = "https://www.androidauthority.com/best-android-13-features-3113880/";

        // Selenium. Using Selenium because jsoup doesn't handle JS and lazy loading.
        System.setProperty("webdriver.chrome.driver", "/home/dave/chromedriver");
        ChromeOptions options = new ChromeOptions();options.addArguments("--headless");
        //WebDriver driver = new ChromeDriver(options);

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

        // Check it's working
        System.out.println(ease + " " + grade);

        // Testing the sizing of the document for Azure
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>   " + extractedContentPlainText.length());
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>   " + WordUtils.wrap(extractedContentPlainText, 40));

        // ToDo: Add user, title, excerpt, byline, content, ease, grade and tag array to model.

        // Return the clean HTML
        return extractedContentHtml;
    }
}
