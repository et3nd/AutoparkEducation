package com.education.web.controller;

import com.education.entity.PublicTransport;
import com.education.service.PublicTransportService;
import com.education.web.response.ErrorResponse;
import com.education.web.response.SuccessResponse;
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
    public ResponseEntity addPublicTransport(@RequestBody PublicTransport transport) {
        try {
            SuccessResponse successResponse = new SuccessResponse();
            transportService.addPublicTransport(transport);
            return ResponseEntity.ok(successResponse);
        } catch (SQLException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<SuccessResponse> updatePublicTransport(@RequestBody PublicTransport transport) {
        SuccessResponse successResponse = new SuccessResponse();
        transportService.updatePublicTransport(transport);
        return ResponseEntity.ok(successResponse);
    }

    @DeleteMapping("/{transportNumber}")
    public ResponseEntity<SuccessResponse> removePublicTransport(@PathVariable int transportNumber) {
        SuccessResponse successResponse = new SuccessResponse();
        transportService.removePublicTransport(transportNumber);
        return ResponseEntity.ok(successResponse);
    }

    @GetMapping("/{transportNumber}")
    public ResponseEntity<PublicTransport> getPublicTransport(@PathVariable int transportNumber) {
        return ResponseEntity.ok(transportService.getPublicTransport(transportNumber));
    }
}
