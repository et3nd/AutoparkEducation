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
        try {
            routeService.addRoute(route);
            return ResponseEntity.ok(new Response("Success"));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateRoute(@RequestBody Route route) {
        routeService.updateRoute(route);
        return ResponseEntity.ok(new Response("Success"));
    }

    @DeleteMapping("/{routeNumber}")
    public ResponseEntity<Response> removeRoute(@PathVariable int routeNumber) {
        routeService.removeRoute(routeNumber);
        return ResponseEntity.ok(new Response("Success"));
    }

    @GetMapping("/{routeNumber}")
    public ResponseEntity<Route> getRoute(@PathVariable int routeNumber) {
        return ResponseEntity.ok(routeService.getRoute(routeNumber));
    }
}
