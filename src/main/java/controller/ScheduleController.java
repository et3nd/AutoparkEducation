package controller;

import entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.ScheduleService;

import java.sql.SQLException;

@Controller
@RequestMapping(value = "/schedule", produces = "application/json")
public class ScheduleController {
    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSchedule(@RequestBody Schedule schedule) {
        try {
            scheduleService.addSchedule(schedule);
            return new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>("{ \"message\": \"" + e.getMessage() + "\" }", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateSchedule(@RequestBody Schedule schedule) {
        scheduleService.updateSchedule(schedule);
        return new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeSchedule(@PathVariable int id) {
        scheduleService.removeSchedule(id);
        return new ResponseEntity<>("{ \"message\": \"Success\" }", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable int id) {
        return new ResponseEntity<>(scheduleService.getSchedule(id), HttpStatus.OK);
    }
}
