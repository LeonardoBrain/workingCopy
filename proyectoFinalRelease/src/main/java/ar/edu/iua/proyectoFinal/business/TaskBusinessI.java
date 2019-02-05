package ar.edu.iua.proyectoFinal.business;

import ar.edu.iua.proyectoFinal.model.Task;
import ar.edu.iua.proyectoFinal.model.TaskList;
import ar.edu.iua.proyectoFinal.model.exception.BusinessException;
import ar.edu.iua.proyectoFinal.model.exception.NotFoundException;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface TaskBusinessI {

    Task add (Task task) throws BusinessException;
    Task update (Task task, boolean isAdmin) throws BusinessException, NotFoundException;
    void delete (Task task) throws NotFoundException;
    Task getOne (int id) throws NotFoundException;


    List <Task> getAllTasksByTaskList(List<Integer> taskListIds) throws NotFoundException;
    List<Task> getAllTasks() throws BusinessException, NotFoundException;
    List<Task> getAllTasksByDate() throws BusinessException, NotFoundException;
    List<Task> getAllTasksByPriority() throws BusinessException, NotFoundException;
    List<Task> getAllTasksByListNameByDate(String list_name)throws BusinessException, NotFoundException;
    List<Task> getAllTasksByListNameByPriority(String list_name) throws BusinessException, NotFoundException;
    List<Task> getAllTasksByListName(String list_name) throws BusinessException, NotFoundException;
    List<Task> getAllTasksByListAndSprintName(String sprintName, String listName, String orderBy) throws NotFoundException;

}
