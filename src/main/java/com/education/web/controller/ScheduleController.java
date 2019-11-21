package com.education.web.controller;

import com.education.entity.Schedule;
import com.education.service.ScheduleService;
import com.education.web.response.ErrorResponse;
import com.education.web.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/schedule", produces = "application/json")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/add")
    public ResponseEntity addSchedule(@RequestBody Schedule schedule) {
        try {
            SuccessResponse successResponse = new SuccessResponse();
            scheduleService.addSchedule(schedule);
            return ResponseEntity.ok(successResponse);
        } catch (SQLException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<SuccessResponse> updateSchedule(@RequestBody Schedule schedule) {
        SuccessResponse successResponse = new SuccessResponse();
        scheduleService.updateSchedule(schedule);
        return ResponseEntity.ok(successResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> removeSchedule(@PathVariable int id) {
        SuccessResponse successResponse = new SuccessResponse();
        scheduleService.removeSchedule(id);
        return ResponseEntity.ok(successResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable int id) {
        return ResponseEntity.ok(scheduleService.getSchedule(id));
    }
}
