package com.education.web.controller;

import com.education.entity.Driver;
import com.education.service.DriverService;
import com.education.web.response.ErrorResponse;
import com.education.web.response.SuccessResponse;
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
    public ResponseEntity addDriver(@RequestBody Driver driver) {
        try {
            SuccessResponse successResponse = new SuccessResponse();
            driverService.addDriver(driver);
            return ResponseEntity.ok(successResponse);
        } catch (SQLException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/update")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public ResponseEntity<SuccessResponse> updateDriver(@RequestBody Driver driver) {
        SuccessResponse successResponse = new SuccessResponse();
        driverService.updateDriver(driver);
        return ResponseEntity.ok(successResponse);
    }

    @DeleteMapping("/{license}")
    public ResponseEntity<SuccessResponse> removeDriver(@PathVariable int license) {
        SuccessResponse successResponse = new SuccessResponse();
        driverService.removeDriver(license);
        return ResponseEntity.ok(successResponse);
    }

    @GetMapping("/{license}")
    public ResponseEntity<Driver> getDriver(@PathVariable int license) {
        return ResponseEntity.ok(driverService.getDriver(license));
    }
}
