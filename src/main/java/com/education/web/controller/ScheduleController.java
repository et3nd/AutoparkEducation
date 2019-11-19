package com.education.web.controller;

import com.education.entity.Schedule;
import com.education.service.ScheduleService;
import com.education.web.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/schedule", produces = "application/json")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final Response response;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, Response response) {
        this.scheduleService = scheduleService;
        this.response = response;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addSchedule(@RequestBody Schedule schedule) {
        try {
            scheduleService.addSchedule(schedule);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (SQLException e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateSchedule(@RequestBody Schedule schedule) {
        scheduleService.updateSchedule(schedule);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> removeSchedule(@PathVariable int id) {
        scheduleService.removeSchedule(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable int id) {
        return new ResponseEntity<>(scheduleService.getSchedule(id), HttpStatus.OK);
    }
}
