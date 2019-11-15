package controller;

import entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.RouteService;

import java.sql.SQLException;

@Controller
@RequestMapping(value = "/route", produces = "application/json")
public class RouteController {
    private RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRoute(@RequestBody Route route) {
        try {
            routeService.addRoute(route);
            return new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>("{ \"message\": \"" + e.getMessage() + "\" }", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateRoute(@RequestBody Route route) {
        routeService.updateRoute(route);
        return new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);
    }

    @DeleteMapping("/{routeNumber}")
    public ResponseEntity<String> removeRoute(@PathVariable int routeNumber) {
        routeService.removeRoute(routeNumber);
        return new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);
    }

    @GetMapping("/{routeNumber}")
    public ResponseEntity<Route> getRoute(@PathVariable int routeNumber) {
        return new ResponseEntity<>(routeService.getRoute(routeNumber), HttpStatus.OK);
    }
}
