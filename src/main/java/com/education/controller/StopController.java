package com.education.controller;

import com.education.entity.Stop;
import com.education.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/stop", produces = "application/json")
public class StopController {
    private final StopService stopService;
    private ResponseEntity<String> response = new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);

    @Autowired
    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStop(@RequestBody Stop stop) {
        try {
            stopService.addStop(stop);
            return response;
        } catch (SQLException e) {
            return new ResponseEntity<>("{ \"message\": \"" + e.getMessage() + "\" }", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateStop(@RequestBody Stop stop) {
        stopService.updateStop(stop);
        return response;
    }

    @DeleteMapping("/{stopName}")
    public ResponseEntity<String> removeStop(@PathVariable String stopName) {
        stopService.removeStop(stopName);
        return response;
    }

    @GetMapping("/{stopName}")
    public ResponseEntity<Stop> getStop(@PathVariable String stopName) {
        return new ResponseEntity<>(stopService.getStop(stopName), HttpStatus.OK);
    }
}
