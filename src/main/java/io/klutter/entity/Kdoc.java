package io.klutter.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Kdoc")
public class Kdoc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "excerpt")
    private String excerpt;

    @Column(name = "byline")
    private String byline;

    @Column(name = "content")
    private String content;

    @Column(name = "ease")
    private Float ease;

    @Column(name = "grade")
    private Float grade;

    @Column(name = "pdf")
    @Lob
    private String pdf;


    public Kdoc() {
    }

    public Kdoc(String url, String title, String excerpt, String byline, String content, Float ease, Float grade, String pdf) {
        this.title = title;
        this.url = url;
        this.excerpt = excerpt;
        this.byline = byline;
        this.content = content;
        this.ease = ease;
        this.grade = grade;
        this.pdf = pdf;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() { return url;}

    public void setUrl(String url) { this.url = url; }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getEase() {
        return ease;
    }

    public void setEase(Float ease) {
        this.ease = ease;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }


    @Override
    public String toString() {
        return "Kdoc{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", byline='" + byline + '\'' +
                ", content='" + content + '\'' +
                ", ease=" + ease +
                ", grade=" + grade +
                ", pdf='" + pdf + '\'' +
                '}';
    }
}