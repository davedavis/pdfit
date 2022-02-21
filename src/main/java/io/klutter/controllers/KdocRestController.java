package io.klutter.controllers;


import io.klutter.models.Kdoc;
import io.klutter.services.KdocService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/kdocs")
public class KdocRestController {

    private final KdocService kdocService;


    public KdocRestController(KdocService kdocService) {
        this.kdocService = kdocService;
    }

    @GetMapping
    public List<Kdoc> getAllKdocs(){
        return kdocService.getAllKdocs();
    }
}
