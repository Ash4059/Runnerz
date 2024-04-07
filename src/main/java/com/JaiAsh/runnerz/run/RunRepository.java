package com.JaiAsh.runnerz.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.JdbcClient.StatementSpec;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll(){
        return jdbcClient.sql("Select * from Run")
                .query(Run.class)
                .list();
    }

    public Optional<Run> findById(Integer id){
        return jdbcClient.sql("Select * from Run where id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public void create(Run run){
        var updated = jdbcClient.sql("INSERT INTO Run(id, title, startDateTime, completedDateTime, miles, location)\n" +
                "VALUES(?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(), run.startDateTime(), run.completedDateTime(), run.miles(), run.location().toString()))
                .update();
        Assert.state(updated == 1, "Failed to create run : " + run.title());
    }

    public void update(Run run){
        var updated = jdbcClient.sql("update Run set title=?, startDateTime=?, completedDateTime=?, miles=?, location=? where id=?")
                .params(List.of(run.title(), run.startDateTime(), run.completedDateTime(), run.miles(), run.location().toString(), run.id()))
                .update();
        Assert.state(updated == 1, "Failed to create run : " + run.title());
    }

    public void delete(Integer id){
        var updated = jdbcClient.sql("delete from Run where id = :id")
                .param("id", id)
                .update();
        Assert.state(updated == 1, "Failed to create run : " + id);
    }

    public int count(){
        return jdbcClient.sql("Select * from Run")
                .query(Run.class)
                .list()
                .size();
    }

    public void SaveAll(List<Run> runs){
        runs.stream().forEach((Run run)-> {create(run);});
    }

    public List<Run> findByLLocation(String location){
        return jdbcClient.sql("Select * from Run where location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }
}