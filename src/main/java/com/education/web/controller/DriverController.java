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
        Response response = new Response();
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
        Response response = new Response();
        driverService.updateDriver(driver);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{license}")
    public ResponseEntity<Response> removeDriver(@PathVariable int license) {
        Response response = new Response();
        driverService.removeDriver(license);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{license}")
    public ResponseEntity<Driver> getDriver(@PathVariable int license) {
        return new ResponseEntity<>(driverService.getDriver(license), HttpStatus.OK);
    }
}
