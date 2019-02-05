package ar.edu.iua.proyectoFinal.business;

import ar.edu.iua.proyectoFinal.model.TaskList;
import ar.edu.iua.proyectoFinal.model.exception.BusinessException;
import ar.edu.iua.proyectoFinal.model.exception.NotFoundException;

import java.util.List;


public interface TaskListBusinessI {

      TaskList add (TaskList taskList) throws BusinessException;
      TaskList getOne (int id) throws NotFoundException ;
      List<TaskList> getAllBySprintName (String sprintName) throws NotFoundException;
      List <String> getAllSprints() throws NotFoundException;
      TaskList getTaskListByNameAndSprintName(String taskListName, String sprintName) throws NotFoundException;
      int getTaskListIdByNameAndSprintName(String taskListName, String sprintName) throws NotFoundException;
}
