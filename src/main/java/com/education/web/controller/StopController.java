package com.education.web.controller;

import com.education.entity.Stop;
import com.education.service.StopService;
import com.education.web.response.ErrorResponse;
import com.education.web.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/stop", produces = "application/json")
public class StopController {
    private final StopService stopService;

    @Autowired
    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @PostMapping("/add")
    public ResponseEntity addStop(@RequestBody Stop stop) {
        try {
            SuccessResponse successResponse = new SuccessResponse();
            stopService.addStop(stop);
            return ResponseEntity.ok(successResponse);
        } catch (SQLException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<SuccessResponse> updateStop(@RequestBody Stop stop) {
        SuccessResponse successResponse = new SuccessResponse();
        stopService.updateStop(stop);
        return ResponseEntity.ok(successResponse);
    }

    @DeleteMapping("/{stopName}")
    public ResponseEntity<SuccessResponse> removeStop(@PathVariable String stopName) {
        SuccessResponse successResponse = new SuccessResponse();
        stopService.removeStop(stopName);
        return ResponseEntity.ok(successResponse);
    }

    @GetMapping("/{stopName}")
    public ResponseEntity<Stop> getStop(@PathVariable String stopName) {
        return ResponseEntity.ok(stopService.getStop(stopName));
    }
}
