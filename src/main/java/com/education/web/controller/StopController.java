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
        try {
            stopService.addStop(stop);
            return ResponseEntity.ok(new Response("Success"));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateStop(@RequestBody Stop stop) {
        stopService.updateStop(stop);
        return ResponseEntity.ok(new Response("Success"));
    }

    @DeleteMapping("/{stopName}")
    public ResponseEntity<Response> removeStop(@PathVariable String stopName) {
        stopService.removeStop(stopName);
        return ResponseEntity.ok(new Response("Success"));
    }

    @GetMapping("/{stopName}")
    public ResponseEntity<Stop> getStop(@PathVariable String stopName) {
        return ResponseEntity.ok(stopService.getStop(stopName));
    }
}
