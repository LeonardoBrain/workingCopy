package ar.edu.iua.proyectoFinal.model;



import javax.persistence.*;



@Entity
public class Task implements Comparable<Task> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int taskId;

    private String name;
    private String creationDate;
    private String modificactionDate;
    private String priority;
    private double estimatedTime;


    @ManyToOne
    @JoinColumn(name = "taskListId", nullable = false)
    private TaskList taskList;

    public Task(String name, String creationDate, String modificactionDate, double estimatedTime, TaskList taskList, String priority) {
        this.name = name;
        this.creationDate = creationDate;
        this.modificactionDate = modificactionDate;
        this.priority = priority;
        this.estimatedTime = estimatedTime;
        this.taskList = taskList;
    }

    public Task() {
    }

    public Task(String name, String priority) {
        this.name = name;
        this.priority = priority;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getModificactionDate() {
        return modificactionDate;
    }

    public void setModificactionDate(String modificactionDate) {
        this.modificactionDate = modificactionDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public double getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(double estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public Integer convertPriorityToNumber() {

        switch (priority) {

            case "High":
                return 3;
            case "Medium":
                return 2;
            case "Low":
                return 1;
            default:
                break;


        }


        return 0;
    }


    @Override
    public int compareTo(Task o) {


        return o.convertPriorityToNumber().compareTo(this.convertPriorityToNumber());

    }
}
