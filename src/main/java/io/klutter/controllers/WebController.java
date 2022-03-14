package io.klutter.controllers;

import io.klutter.models.Kdoc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequestMapping("/")
public class WebController {

    // ToDo: Proper form: https://www.wimdeblauwe.com/blog/2021/05/23/form-handling-with-thymeleaf/

    WebClient webClient = WebClient.create("http://localhost:8080/declutter");


    /* Homepage. Added ModelAttribute, so I could use ThymeLeaf
    and have the user submit a URL as a Kdoc object right from
    the homepage form.*/
    @GetMapping()
    public String index(@ModelAttribute Kdoc kdoc, Model model) {
        model.addAttribute("kdoc", kdoc);
        return "index";
    }


    private final String URI_DECLUTTER = "http://localhost:8080/declutter";


//    @PostMapping()
//    public ModelAndView homeDeclutter(@ModelAttribute ("Kdoc") Kdoc kdoc, Model model) {
//        System.out.println(kdoc);
//        RestTemplate restTemplate = new RestTemplate();
//        Kdoc createdKdoc = restTemplate.postForObject(URI_DECLUTTER, kdoc, Kdoc.class);
//        System.out.println(createdKdoc);
//        ModelAndView mav = new ModelAndView("kdoc");
//
//        model.addAttribute("dave", createdKdoc);
//        mav.addObject("kdoc", createdKdoc);
//
//        return mav;
//
//    }


    @PostMapping()
    public String homeDeclutter(@ModelAttribute ("Kdoc") Kdoc kdoc, Model model) {
        System.out.println(kdoc);
        RestTemplate restTemplate = new RestTemplate();
        Kdoc createdKdoc = restTemplate.postForObject(URI_DECLUTTER, kdoc, Kdoc.class);
        System.out.println(createdKdoc);
        model.addAttribute("createdKdoc", createdKdoc);

        return "kdoc-template";

    }










//    private final KdocService kdocService;
//
//    public WebController(KdocService kdocService) {
//        this.kdocService = kdocService;
//    }
//
//    @GetMapping
//    public List<Kdoc> listAllKdocs(){
//        return kdocService.getAllKdocs();
//    }
}
