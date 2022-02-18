package io.pdfit.pdfservice;
import com.chimbori.crux.articles.Article;
import com.chimbori.crux.articles.ArticleExtractor;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.params.Param;
import net.dankito.readability4j.Readability4J;
import okhttp3.HttpUrl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class PdfService {

    @RequestMapping("/pdf")
    public String index() throws IOException {

        // Selenium
        System.setProperty("webdriver.chrome.driver", "/home/dave/chromedriver");
        ChromeOptions options = new ChromeOptions();options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://realpython.com/python-sockets/");

        String html = driver.getPageSource();
//        Document doc = Jsoup.parse(html);

        // Jsoup
        String url = "https://realpython.com/python-sockets/";
//        Document doc = Jsoup.connect("https://realpython.com/python-sockets/").get();
        Document doc = Jsoup.parse(html);

        // Readability
        Readability4J readability4J = new Readability4J(url, html); // url is just needed to resolve relative urls
        net.dankito.readability4j.Article article = readability4J.parse();

        // returns extracted content in a <div> element
        String extractedContentHtml = article.getContent();
        // to get content wrapped in <html> tags and encoding set to UTF-8, see chapter 'Output encoding'
        String extractedContentHtmlWithUtf8Encoding = article.getContentWithUtf8Encoding();
        String extractedContentPlainText = article.getTextContent();
        String title = article.getTitle();
        String byline = article.getByline();
        String excerpt = article.getExcerpt();


//        // Crux
//        Article article = new ArticleExtractor(HttpUrl.parse(url), doc)
//                .extractMetadata()
//                .extractContent()
//                .getArticle();






//        return article.getContent();
        return extractedContentHtml;
    }

    @RequestMapping("/pdf/abc")

    public String index2(){

        String url = "https://realpython.com/python-sockets/";
        Document doc = null;
        try {
            doc = Jsoup.connect("https://realpython.com/python-sockets/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Article article = new ArticleExtractor(HttpUrl.parse(url), doc)
                .extractMetadata()
                .extractContent()
                .getArticle();

        System.out.println(article.toString());
        String safe = Jsoup.clean(doc.html(), Safelist.basic());



//        BufferedWriter writer = null;
//        try
//        {
//            Document d = Jsoup.connect("https://realpython.com/python-sockets/").get();
//            writer = new BufferedWriter( new FileWriter("input.html"));
//            writer.write(d.html());
//            writer.close();  // add this line
//        }
//        catch ( IOException e)
//        {
//            System.out.println(e);
//        }


        Pdf pdf = new Pdf();

//        pdf.addPageFromString(doc.toString());
                pdf.addPageFromUrl("https://realpython.com/python-sockets/");

        // Add a Table of Contents
        pdf.addToc();

        // The `wkhtmltopdf` shell command accepts different types of options such as global, page, headers and footers, and toc. Please see `wkhtmltopdf -H` for a full explanation.
        // All options are passed as array, for example:
        //        pdf.addParam(new Param("--no-footer-line"), new Param("--header-html", "file:///header.html"));
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

