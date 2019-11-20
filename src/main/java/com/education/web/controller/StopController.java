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

    @Autowired
    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addStop(@RequestBody Stop stop) {
        Response response = new Response();
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
        Response response = new Response();
        stopService.updateStop(stop);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{stopName}")
    public ResponseEntity<Response> removeStop(@PathVariable String stopName) {
        Response response = new Response();
        stopService.removeStop(stopName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{stopName}")
    public ResponseEntity<Stop> getStop(@PathVariable String stopName) {
        return new ResponseEntity<>(stopService.getStop(stopName), HttpStatus.OK);
    }
}
