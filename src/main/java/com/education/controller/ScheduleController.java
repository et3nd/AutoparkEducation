package com.education.controller;

import com.education.entity.Schedule;
import com.education.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/schedule", produces = "application/json")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private ResponseEntity<String> response = new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSchedule(@RequestBody Schedule schedule) {
        try {
            scheduleService.addSchedule(schedule);
            return response;
        } catch (SQLException e) {
            return new ResponseEntity<>("{ \"message\": \"" + e.getMessage() + "\" }", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateSchedule(@RequestBody Schedule schedule) {
        scheduleService.updateSchedule(schedule);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeSchedule(@PathVariable int id) {
        scheduleService.removeSchedule(id);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable int id) {
        return new ResponseEntity<>(scheduleService.getSchedule(id), HttpStatus.OK);
    }
}
