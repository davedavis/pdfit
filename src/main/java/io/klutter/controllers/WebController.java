package io.klutter.controllers;

import io.klutter.models.Kdoc;
import io.klutter.services.KdocService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class WebController {

    /* Homepage. Added ModelAttribute, so I could use ThymeLeaf
    and have the user submit a URL as a Kdoc object right from
    the homepage form.*/
    @GetMapping()
    public String index(@ModelAttribute Kdoc kdoc, Model model) {
        model.addAttribute("kdoc", kdoc);
        return "index";
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
