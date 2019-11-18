package com.education.controller;

import com.education.entity.Route;
import com.education.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/route", produces = "application/json")
public class RouteController {
    private final RouteService routeService;
    private ResponseEntity<String> response = new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRoute(@RequestBody Route route) {
        try {
            routeService.addRoute(route);
            return response;
        } catch (SQLException e) {
            return new ResponseEntity<>("{ \"message\": \"" + e.getMessage() + "\" }", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateRoute(@RequestBody Route route) {
        routeService.updateRoute(route);
        return response;
    }

    @DeleteMapping("/{routeNumber}")
    public ResponseEntity<String> removeRoute(@PathVariable int routeNumber) {
        routeService.removeRoute(routeNumber);
        return response;
    }

    @GetMapping("/{routeNumber}")
    public ResponseEntity<Route> getRoute(@PathVariable int routeNumber) {
        return new ResponseEntity<>(routeService.getRoute(routeNumber), HttpStatus.OK);
    }
}
