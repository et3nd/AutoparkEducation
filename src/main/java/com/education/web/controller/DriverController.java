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
    private final Response response;

    @Autowired
    public DriverController(DriverService driverService, Response response) {
        this.driverService = driverService;
        this.response = response;
    }

    @PostMapping("/add")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public ResponseEntity<Response> addDriver(@RequestBody Driver driver) {
        try {
            driverService.addDriver(driver);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (SQLException e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public ResponseEntity<Response> updateDriver(@RequestBody Driver driver) {
        driverService.updateDriver(driver);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{license}")
    public ResponseEntity<Response> removeDriver(@PathVariable int license) {
        driverService.removeDriver(license);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{license}")
    public ResponseEntity<Driver> getDriver(@PathVariable int license) {
        return new ResponseEntity<>(driverService.getDriver(license), HttpStatus.OK);
    }
}
