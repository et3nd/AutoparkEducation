package com.education.controller;

import com.education.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.education.service.DriverService;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/driver", produces = "application/json")
public class DriverController {
    private final DriverService driverService;
    private ResponseEntity<String> response = new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/add")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public ResponseEntity<String> addDriver(@RequestBody Driver driver) {
        try {
            driverService.addDriver(driver);
            return response;
        } catch (SQLException e) {
            return new ResponseEntity<>("{ \"message\": \"" + e.getMessage() + "\" }", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public ResponseEntity<String> updateDriver(@RequestBody Driver driver) {
        driverService.updateDriver(driver);
        return response;
    }

    @DeleteMapping("/{license}")
    public ResponseEntity<String> removeDriver(@PathVariable int license) {
        driverService.removeDriver(license);
        return response;
    }

    @GetMapping("/{license}")
    public ResponseEntity<Driver> getDriver(@PathVariable int license) {
        return new ResponseEntity<>(driverService.getDriver(license), HttpStatus.OK);
    }
}
