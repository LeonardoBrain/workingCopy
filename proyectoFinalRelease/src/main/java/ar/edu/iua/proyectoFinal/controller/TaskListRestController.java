package ar.edu.iua.proyectoFinal.controller;

import ar.edu.iua.proyectoFinal.business.TaskListBusinessI;
import ar.edu.iua.proyectoFinal.model.Task;
import ar.edu.iua.proyectoFinal.model.TaskList;
import ar.edu.iua.proyectoFinal.model.exception.BusinessException;
import ar.edu.iua.proyectoFinal.model.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.List;

@RestController
@RequestMapping(Constants.TASK_LIST_URL)
public class TaskListRestController {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private TaskListBusinessI taskListBusiness;



    @PostMapping(value = { "", "/" })
    public ResponseEntity <TaskList>addListTask(@RequestBody TaskList taskList) {
        try {
            //System.out.println("task:"+ taskList.getTaskListId());
            taskListBusiness.add(taskList);
            HttpHeaders responseHeaders =  new HttpHeaders();
            responseHeaders.set("location", "/tasklist/" + taskList.getTaskListId());
            return new ResponseEntity<TaskList>(responseHeaders, HttpStatus.CREATED);
        } catch (BusinessException e) {
            log.error("Error al realizar el request");
            return new ResponseEntity<TaskList>( HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("Error interno del server");
            return new ResponseEntity<TaskList>( HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @GetMapping("/{id}")
    public ResponseEntity<TaskList> getTaskList(@PathVariable int id){
        TaskList taskList;

       try {
           taskList= taskListBusiness.getOne(id);

       }catch (NotFoundException e){
           log.error("Error no se encontro la lista");
           return new ResponseEntity<TaskList>(HttpStatus.NOT_FOUND);
       }


        return new ResponseEntity<TaskList>(taskList, HttpStatus.OK);

    }

    @GetMapping({"/",""})
    public ResponseEntity<List<TaskList>> getAllTaskListsBySprint(@RequestParam(required = false, value = "sprint_name", defaultValue = "*") String sprintName){
        List<TaskList> tl;

        try{
            tl = taskListBusiness.getAllBySprintName(sprintName);
        }catch (NotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(tl,HttpStatus.OK);


    }

    @GetMapping({"/sprints"})
    public ResponseEntity <List <String>> getAllSprints (){

        List<String> sprints;

        try{
            sprints = taskListBusiness.getAllSprints();
        }catch (NotFoundException e){

            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(sprints,HttpStatus.OK);

    }




}
