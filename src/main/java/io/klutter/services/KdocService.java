package io.klutter.services;

import io.klutter.data.KdocRepository;
import io.klutter.models.Kdoc;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class KdocService {

    @Autowired
    KdocRepository kdocRepository;

//    private final KdocRepository kdocRepository;
//
//    public KdocService(KdocRepository kdocRepository) {
//        this.kdocRepository = kdocRepository;
//    }

    public List<Kdoc> getAllKdocs(){
        return kdocRepository.findAll();
    }


    @RequestMapping("/kdocs")
    public List<Kdoc> listAllKdocs() {
        return kdocRepository.findAll();
    }

    @RequestMapping("/kdocs/{kdocid}")
    public Optional<Kdoc> listKdocsById(@PathVariable Long kdocid){

        return kdocRepository.findById(kdocid);
    }


    // ToDo: Insert actual genertor logic here.
    @RequestMapping(value = "/kdocs", method = RequestMethod.POST)
    ResponseEntity<Kdoc> generateKdoc(@RequestBody Kdoc kdoc){
        Kdoc savedKdoc = kdocRepository.save(kdoc);
        return new ResponseEntity<Kdoc>(savedKdoc, HttpStatus.OK);
    }

// WORKING
//    @RequestMapping(value = "/kdocs/url", method = RequestMethod.POST)
//    ResponseEntity<String> generateKdocFromUrl(@RequestBody String url, @RequestParam(name = "url") String articleurl) {
//        // Decode the URL encoded string if it's encoded.
//        String result = java.net.URLDecoder.decode(url, StandardCharsets.UTF_8);
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>  " + articleurl );
////        Kdoc savedKdoc = kdocRepository.save(kdoc);
//        return new ResponseEntity<String>(url, HttpStatus.OK);
//    }

    @RequestMapping(value = "/kdocs/url", method = RequestMethod.POST)
    ResponseEntity<Kdoc> generateKdocFromUrl(@RequestBody Kdoc url) {
        // Decode the URL encoded string if it's encoded.
        String result = java.net.URLDecoder.decode(url.getUrl(), StandardCharsets.UTF_8);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>  " + result);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>  " + url.getUrl());
//        Kdoc savedKdoc = kdocRepository.save(kdoc);
        return new ResponseEntity<Kdoc>(url, HttpStatus.OK);
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

