package com.education.web.controller;

import com.education.entity.PublicTransport;
import com.education.service.PublicTransportService;
import com.education.web.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/transport", produces = "application/json")
public class PublicTransportController {
    private final PublicTransportService transportService;

    @Autowired
    public PublicTransportController(PublicTransportService transportService) {
        this.transportService = transportService;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addPublicTransport(@RequestBody PublicTransport transport) {
        Response response = new Response();
        try {
            transportService.addPublicTransport(transport);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (SQLException e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updatePublicTransport(@RequestBody PublicTransport transport) {
        Response response = new Response();
        transportService.updatePublicTransport(transport);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{transportNumber}")
    public ResponseEntity<Response> removePublicTransport(@PathVariable int transportNumber) {
        Response response = new Response();
        transportService.removePublicTransport(transportNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{transportNumber}")
    public ResponseEntity<PublicTransport> getPublicTransport(@PathVariable int transportNumber) {
        return new ResponseEntity<>(transportService.getPublicTransport(transportNumber), HttpStatus.OK);
    }
}
