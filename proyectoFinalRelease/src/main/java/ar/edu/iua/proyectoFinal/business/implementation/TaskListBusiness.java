package ar.edu.iua.proyectoFinal.business.implementation;

import ar.edu.iua.proyectoFinal.business.TaskListBusinessI;
import ar.edu.iua.proyectoFinal.model.TaskList;
import ar.edu.iua.proyectoFinal.model.exception.BusinessException;
import ar.edu.iua.proyectoFinal.model.exception.NotFoundException;
import ar.edu.iua.proyectoFinal.model.persistance.TaskListRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Optional;

@Service
public class TaskListBusiness implements TaskListBusinessI {

    @Autowired
    TaskListRepository taskListRepository;


    @Override
    public TaskList add(TaskList taskList) throws BusinessException {

        TaskList prueba = taskListRepository.findByNameAndSprintName(taskList.getName(), taskList.getSprintName());

        if (prueba == null) {

            switch (taskList.getName()) {

                case "Backlog":
                case "TODO":
                case "In Progress":
                case "Done":
                case "Waiting":
                    return taskListRepository.save(taskList);
                default:
                    throw new BusinessException();

            }
        }
        throw  new BusinessException();
    }

    @Override
    public TaskList getOne(int id) throws NotFoundException {

        Optional<TaskList> pr = taskListRepository.findById(id);

        if (pr.isPresent()) {
            return pr.get();
        }else{
            throw new NotFoundException();
        }

    }

    public List<TaskList> getAllBySprintName(String sprintName) throws NotFoundException{

        List <TaskList> tl = taskListRepository.findBySprintName(sprintName);

        if(!tl.isEmpty()){

            return tl;
        }else{

            throw new NotFoundException();
        }
    }

    public List<String> getAllSprints() throws NotFoundException{

        List<String> sprints =  taskListRepository.findAllSprints();
        if(!sprints.isEmpty()){

            return sprints;
        }else{

            throw new NotFoundException();
        }
    }

    public TaskList getTaskListByNameAndSprintName(String taskListName, String sprintName) throws NotFoundException{

        return taskListRepository.findByNameAndSprintName(taskListName, sprintName);
    }
}
