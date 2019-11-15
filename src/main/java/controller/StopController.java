package controller;

import entity.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.StopService;

import java.sql.SQLException;

@Controller
@RequestMapping(value = "/stop", produces = "application/json")
public class StopController {
    private StopService stopService;

    @Autowired
    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStop(@RequestBody Stop stop) {
        try {
            stopService.addStop(stop);
            return new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>("{ \"message\": \"" + e.getMessage() + "\" }", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateStop(@RequestBody Stop stop) {
        stopService.updateStop(stop);
        return new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);
    }

    @DeleteMapping("/{stopName}")
    public ResponseEntity<String> removeStop(@PathVariable String stopName) {
        stopService.removeStop(stopName);
        return new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);
    }

    @GetMapping("/{stopName}")
    public ResponseEntity<Stop> getStop(@PathVariable String stopName) {
        return new ResponseEntity<>(stopService.getStop(stopName), HttpStatus.OK);
    }
}
