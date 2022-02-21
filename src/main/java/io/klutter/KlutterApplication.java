package io.klutter;

import io.klutter.data.KdocRepository;
import io.klutter.models.Kdoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

//Add the scanBasePackages parameter to the annotation as I added my services in
//separate packages so, they need to be configured on application start.
@SpringBootApplication(scanBasePackages = {"io.klutter.models", "io.klutter.controllers", "io.klutter.data", "io.klutter.services"} )
public class KlutterApplication {

    private final KdocRepository kdocRepository;

    public KlutterApplication(KdocRepository kdocRepository) {
        this.kdocRepository = kdocRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(KlutterApplication.class, args);
    }

    // So I can visualize data access working.
    // So I can wait for Spring to set up the DB before I start playing around with DB.
    @EventListener(ApplicationReadyEvent.class)
    public void EventListenerExecute() {
        Iterable<Kdoc> kdocs = this.kdocRepository.findAll();
        kdocs.forEach(System.out::println);
    }

}
