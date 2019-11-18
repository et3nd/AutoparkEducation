package com.education.controller;

import com.education.entity.PublicTransport;
import com.education.service.PublicTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/transport", produces = "application/json")
public class PublicTransportController {
    private final PublicTransportService transportService;
    private ResponseEntity<String> response = new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);

    @Autowired
    public PublicTransportController(PublicTransportService transportService) {
        this.transportService = transportService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPublicTransport(@RequestBody PublicTransport transport) {
        try {
            transportService.addPublicTransport(transport);
            return response;
        } catch (SQLException e) {
            return new ResponseEntity<>("{ \"message\": \"" + e.getMessage() + "\" }", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePublicTransport(@RequestBody PublicTransport transport) {
        transportService.updatePublicTransport(transport);
        return response;
    }

    @DeleteMapping("/{transportNumber}")
    public ResponseEntity<String> removePublicTransport(@PathVariable int transportNumber) {
        transportService.removePublicTransport(transportNumber);
        return response;
    }

    @GetMapping("/{transportNumber}")
    public ResponseEntity<PublicTransport> getPublicTransport(@PathVariable int transportNumber) {
        return new ResponseEntity<>(transportService.getPublicTransport(transportNumber), HttpStatus.OK);
    }
}
