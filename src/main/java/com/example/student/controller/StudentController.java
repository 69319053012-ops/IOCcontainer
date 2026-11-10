package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.service.InMemoryStudentService;
import com.example.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService = new InMemoryStudentService();

    @GetMapping("/students")
    public List<Student> list() {
        return studentService.findAll();
    }

    @GetMapping("/student")
    public ResponseEntity<Student> one(@RequestParam Long id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
