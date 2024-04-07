package com.JaiAsh.runnerz.run;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }
    
    @GetMapping("")
    List<Run> findAll(){
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> run = runRepository.findById(id);
        if(run.isEmpty()) {
            throw new RunNotFoundException();
        }
        return run.get();
    }

    @PostMapping("")
    void create(@Valid @RequestBody Run run, HttpServletResponse response) {
        runRepository.create(run);
        response.setStatus(HttpStatus.CREATED.value());
    }

    @PutMapping("")
    void update(@Valid @RequestBody Run run, HttpServletResponse response){
        runRepository.update(run);
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id, HttpServletResponse response){
        runRepository.delete(id);
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }

}
