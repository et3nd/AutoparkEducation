package com.education.web.controller;

import com.education.entity.Route;
import com.education.service.RouteService;
import com.education.web.response.ErrorResponse;
import com.education.web.response.SuccessResponse;
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
    public ResponseEntity addRoute(@RequestBody Route route) {
        try {
            SuccessResponse successResponse = new SuccessResponse();
            routeService.addRoute(route);
            return ResponseEntity.ok(successResponse);
        } catch (SQLException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<SuccessResponse> updateRoute(@RequestBody Route route) {
        SuccessResponse successResponse = new SuccessResponse();
        routeService.updateRoute(route);
        return ResponseEntity.ok(successResponse);
    }

    @DeleteMapping("/{routeNumber}")
    public ResponseEntity<SuccessResponse> removeRoute(@PathVariable int routeNumber) {
        SuccessResponse successResponse = new SuccessResponse();
        routeService.removeRoute(routeNumber);
        return ResponseEntity.ok(successResponse);
    }

    @GetMapping("/{routeNumber}")
    public ResponseEntity<Route> getRoute(@PathVariable int routeNumber) {
        return ResponseEntity.ok(routeService.getRoute(routeNumber));
    }
}
