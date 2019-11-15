package controller;

import entity.PublicTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.PublicTransportService;

import java.sql.SQLException;

@Controller
@RequestMapping(value = "/transport", produces = "application/json")
public class PublicTransportController {
    private PublicTransportService transportService;

    @Autowired
    public PublicTransportController(PublicTransportService transportService) {
        this.transportService = transportService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPublicTransport(@RequestBody PublicTransport transport) {
        try {
            transportService.addPublicTransport(transport);
            return new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>("{ \"message\": \"" + e.getMessage() + "\" }", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePublicTransport(@RequestBody PublicTransport transport) {
        transportService.updatePublicTransport(transport);
        return new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);
    }

    @DeleteMapping("/{transportNumber}")
    public ResponseEntity<String> removePublicTransport(@PathVariable int transportNumber) {
        transportService.removePublicTransport(transportNumber);
        return new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);
    }

    @GetMapping("/{transportNumber}")
    public ResponseEntity<PublicTransport> getPublicTransport(@PathVariable int transportNumber) {
        return new ResponseEntity<>(transportService.getPublicTransport(transportNumber), HttpStatus.OK);
    }
}
