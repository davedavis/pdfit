package io.pdfit.declutterservice;
import com.chimbori.crux.articles.Article;
import com.chimbori.crux.articles.ArticleExtractor;
import okhttp3.HttpUrl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DeclutterService {

    @RequestMapping("/")
    public String index() throws IOException {
        String url = "https://realpython.com/python-sockets/";
        Document doc = Jsoup.connect("https://realpython.com/python-sockets/").get();
        Article article = new ArticleExtractor(HttpUrl.parse(url), doc)
                .extractMetadata()
                .extractContent()
                .getArticle();

        System.out.println(article.toString());

        return article.toString();
    }

    @RequestMapping("/abc")
    public String index2(){
        return "Hello from another mapping";
    }
}

