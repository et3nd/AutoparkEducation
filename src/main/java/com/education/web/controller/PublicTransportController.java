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
        try {
            transportService.addPublicTransport(transport);
            return ResponseEntity.ok(new Response("Success"));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updatePublicTransport(@RequestBody PublicTransport transport) {
        transportService.updatePublicTransport(transport);
        return ResponseEntity.ok(new Response("Success"));
    }

    @DeleteMapping("/{transportNumber}")
    public ResponseEntity<Response> removePublicTransport(@PathVariable int transportNumber) {
        transportService.removePublicTransport(transportNumber);
        return ResponseEntity.ok(new Response("Success"));
    }

    @GetMapping("/{transportNumber}")
    public ResponseEntity<PublicTransport> getPublicTransport(@PathVariable int transportNumber) {
        return ResponseEntity.ok(transportService.getPublicTransport(transportNumber));
    }
}
