package com.JaiAsh.runnerz.run;

import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class RunJsonDataLoader implements CommandLineRunner{

    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final RunRepository runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(RunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        if(runRepository.count() == 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")){
                Reader reader = new java.io.InputStreamReader(Objects.requireNonNull(inputStream));
                Run[] runs = objectMapper.readValue(reader, Run[].class);
                runRepository.SaveAll(java.util.Arrays.asList(runs));
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
            }
        }
        
    }
    
}
