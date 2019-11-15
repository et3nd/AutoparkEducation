package controller;

import entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.DriverService;

import java.sql.SQLException;

@Controller
@RequestMapping(value = "/driver", produces = "application/json")
public class DriverController {
    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/add")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public ResponseEntity<String> addDriver(@RequestBody Driver driver) {
        try {
            driverService.addDriver(driver);
            return new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>("{ \"message\": \"" + e.getMessage() + "\" }", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public ResponseEntity<String> updateDriver(@RequestBody Driver driver) {
        driverService.updateDriver(driver);
        return new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);
    }

    @DeleteMapping("/{license}")
    public ResponseEntity<String> removeDriver(@PathVariable int license) {
        driverService.removeDriver(license);
        return new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);
    }

    @GetMapping("/{license}")
    public ResponseEntity<Driver> getDriver(@PathVariable int license) {
        return new ResponseEntity<>(driverService.getDriver(license), HttpStatus.OK);
    }
}
