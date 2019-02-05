package ar.edu.iua.proyectoFinal.controller;


import ar.edu.iua.proyectoFinal.business.TaskBusinessI;
import ar.edu.iua.proyectoFinal.model.Task;
import ar.edu.iua.proyectoFinal.model.exception.BusinessException;
import ar.edu.iua.proyectoFinal.model.exception.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Constants.TASK_URL)
public class TaskRestController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskBusinessI taskBusiness;


    @PostMapping(value = {"", "/"})
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        try {


            taskBusiness.add(task);
            HttpHeaders responseHeaders =  new HttpHeaders();
            responseHeaders.set("location", "/task/" + task.getTaskId());
            return new ResponseEntity<Task>(responseHeaders, HttpStatus.CREATED);
        } catch (BusinessException e) {
            log.error(e.getMessage());
            return new ResponseEntity<Task>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PutMapping(value = {"/",""})
    public ResponseEntity<Task> moveTask(
            @RequestParam(required = false, value = "id", defaultValue = "*") int id,
            @RequestBody Task task,
            HttpServletRequest request) {
        try {
            task.setTaskId(id);

            Task task1 = taskBusiness.update(task, request.isUserInRole("ROLE_LIDER"));
            return new ResponseEntity<Task>(task1, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error(e.getMessage());
            return new ResponseEntity<Task>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e1) {
            log.error(e1.getMessage());
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error interno del server");
            return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable int id) {
        Task task;
        try {

            task = taskBusiness.getOne(id);



        } catch (NotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Task>(task, HttpStatus.OK);

    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false, value = "list_name", defaultValue = "*") String lm,
                                                  @RequestParam(required = false, value = "order_by", defaultValue = "*") String order_by
    ) {
        List<Task> tasks = new ArrayList<>();
        try {

            if (!lm.equals("*")) {
                if (order_by.equals("f")) {
                    tasks = taskBusiness.getAllTasksByListNameByDate(lm);
                } else if (order_by.equals("p")) {
                    tasks = taskBusiness.getAllTasksByListNameByPriority(lm);
                } else {
                    tasks = taskBusiness.getAllTasksByListName(lm);
                }

            } else {
                if (order_by.equals("f")) {
                    tasks = taskBusiness.getAllTasksByDate();
                } else if (order_by.equals("p")) {
                    tasks = taskBusiness.getAllTasksByPriority();
                } else {
                    tasks = taskBusiness.getAllTasks();
                }
            }


        } catch (BusinessException e) {
            log.error(e.getMessage());
            return new ResponseEntity<List<Task>>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            log.error(e.getMessage());


        }
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    @GetMapping(value = {"/all"})
    public ResponseEntity getAllTasksByTaskList(@RequestParam(required = false, value = "list_name", defaultValue = "*") String listName,
                                                @RequestParam(required = false, value = "sprint_name", defaultValue = "*") String sprintName,
                                                @RequestParam(required = false, value = "order_by", defaultValue = "*") String orderBy){

        List<Task> tasks;
        try{
            tasks = taskBusiness.getAllTasksByListAndSprintName(sprintName, listName, orderBy);



        }catch (NotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<List<Task>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tasks, HttpStatus.OK);


    }



    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_LIDER')")
    public ResponseEntity deleteTask(@PathVariable int id) {

        try {
            Task task = new Task();
            task.setTaskId(id);
            taskBusiness.delete(task);



        } catch (NotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity(HttpStatus.OK);

    }


}
