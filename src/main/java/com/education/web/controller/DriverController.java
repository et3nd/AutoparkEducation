package com.education.web.controller;

import com.education.entity.Driver;
import com.education.service.DriverService;
import com.education.web.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/driver", produces = "application/json")
public class DriverController {
    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/add")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public ResponseEntity<Response> addDriver(@RequestBody Driver driver) {
        try {
            driverService.addDriver(driver);
            return ResponseEntity.ok(new Response("Success"));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage()));
        }
    }

    @PutMapping("/update")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public ResponseEntity<Response> updateDriver(@RequestBody Driver driver) {
        driverService.updateDriver(driver);
        return ResponseEntity.ok(new Response("Success"));
    }

    @DeleteMapping("/{license}")
    public ResponseEntity<Response> removeDriver(@PathVariable int license) {
        driverService.removeDriver(license);
        return ResponseEntity.ok(new Response("Success"));
    }

    @GetMapping("/{license}")
    public ResponseEntity<Driver> getDriver(@PathVariable int license) {
        return ResponseEntity.ok(driverService.getDriver(license));
    }
}
