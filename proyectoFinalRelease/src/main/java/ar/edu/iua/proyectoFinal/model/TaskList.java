package ar.edu.iua.proyectoFinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class TaskList {




    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int taskListId;
    private String name;
    private String sprintName;

    @OneToMany(mappedBy="taskList")
    @JsonIgnore
    private List<Task> tasks;


    public TaskList() {

    }


    public TaskList(String name, String sprintName, List<Task> tasks) {
        this.name = name;
        this.sprintName = sprintName;
        this.tasks = tasks;
    }

    public int getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(int taskListId) {
        this.taskListId = taskListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
