package com.education.web.controller;

import com.education.entity.Stop;
import com.education.service.StopService;
import com.education.web.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/stop", produces = "application/json")
public class StopController {
    private final StopService stopService;
    private final Response response;

    @Autowired
    public StopController(StopService stopService, Response response) {
        this.stopService = stopService;
        this.response = response;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addStop(@RequestBody Stop stop) {
        try {
            stopService.addStop(stop);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (SQLException e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateStop(@RequestBody Stop stop) {
        stopService.updateStop(stop);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{stopName}")
    public ResponseEntity<Response> removeStop(@PathVariable String stopName) {
        stopService.removeStop(stopName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{stopName}")
    public ResponseEntity<Stop> getStop(@PathVariable String stopName) {
        return new ResponseEntity<>(stopService.getStop(stopName), HttpStatus.OK);
    }
}
