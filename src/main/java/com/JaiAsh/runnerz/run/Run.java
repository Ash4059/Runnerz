package com.JaiAsh.runnerz.run;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record Run(Integer id, 
                @NotEmpty
                String title, 
                LocalDateTime startDateTime, 
                LocalDateTime completedDateTime, 
                @Positive
                Integer miles, 
                Location location) {
                    public Run{
                        if(completedDateTime.isBefore(completedDateTime)){
                            throw new IllegalArgumentException("completedDateTime cannot be before startDateTime");
                        }
                    }
}
