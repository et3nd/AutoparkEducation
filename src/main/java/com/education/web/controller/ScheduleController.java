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

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addSchedule(@RequestBody Schedule schedule) {
        try {
            scheduleService.addSchedule(schedule);
            return ResponseEntity.ok(new Response("Success"));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateSchedule(@RequestBody Schedule schedule) {
        scheduleService.updateSchedule(schedule);
        return ResponseEntity.ok(new Response("Success"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> removeSchedule(@PathVariable int id) {
        scheduleService.removeSchedule(id);
        return ResponseEntity.ok(new Response("Success"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable int id) {
        return ResponseEntity.ok(scheduleService.getSchedule(id));
    }
}
