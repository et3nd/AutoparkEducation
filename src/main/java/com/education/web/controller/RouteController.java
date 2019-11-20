package com.education.web.controller;

import com.education.entity.Route;
import com.education.service.RouteService;
import com.education.web.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/route", produces = "application/json")
public class RouteController {
    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addRoute(@RequestBody Route route) {
        Response response = new Response();
        try {
            routeService.addRoute(route);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (SQLException e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateRoute(@RequestBody Route route) {
        Response response = new Response();
        routeService.updateRoute(route);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{routeNumber}")
    public ResponseEntity<Response> removeRoute(@PathVariable int routeNumber) {
        Response response = new Response();
        routeService.removeRoute(routeNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{routeNumber}")
    public ResponseEntity<Route> getRoute(@PathVariable int routeNumber) {
        return new ResponseEntity<>(routeService.getRoute(routeNumber), HttpStatus.OK);
    }
}
