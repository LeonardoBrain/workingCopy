package ar.edu.iua.proyectoFinal.business.implementation;

import ar.edu.iua.proyectoFinal.business.TaskBusinessI;
import ar.edu.iua.proyectoFinal.model.Task;
import ar.edu.iua.proyectoFinal.model.exception.BusinessException;
import ar.edu.iua.proyectoFinal.model.exception.NotFoundException;
import ar.edu.iua.proyectoFinal.model.persistance.FactoryDAO;
import ar.edu.iua.proyectoFinal.model.persistance.TaskRepository;
import ar.edu.iua.proyectoFinal.utils.UtilFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TaskBusiness implements TaskBusinessI {


    @Autowired
    UtilFunctions utilFunctions;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task add(Task task) throws BusinessException {

        if (task.getTaskList().getName().equals("Backlog") && (task.getPriority().equals("High") || task.getPriority().equals("Medium") || task.getPriority().equals("Low"))) {

            task.setCreationDate(utilFunctions.getCurrentTimeUsingDate());
            task.setModificactionDate(utilFunctions.getCurrentTimeUsingDate());

            return taskRepository.save(task);

        } else {
            throw new BusinessException();
        }
    }

    @Override
    public Task update(Task task) throws BusinessException, NotFoundException {

        Task noUpdatedTask = getOne(task.getTaskId());
        String originalList = noUpdatedTask.getTaskList().getName();
        String destinationList = task.getTaskList().getName();


        switch (originalList) {

            case "Backlog":
                if (destinationList.equals("TODO") && noUpdatedTask.getEstimatedTime() > 0) {
                    task.setModificactionDate(utilFunctions.getCurrentTimeUsingDate());
                    taskRepository.save(task);
                } else {

                    throw new BusinessException();
                }
                break;

            case "TODO":
            case "In Progress":
            case "Waiting":
                if (!destinationList.equals("Backlog")) {
                    task.setModificactionDate(utilFunctions.getCurrentTimeUsingDate());
                    taskRepository.save(task);
                } else {

                    throw new BusinessException();
                }
                break;
            default:
                throw new BusinessException();


        }

       return task;
    }

    @Override
    public void delete(Task task) throws NotFoundException {
        FactoryDAO.getInstance().getTaskDAO().delete(task);

        if(taskRepository.existsById(task.getTaskId())){

            taskRepository.delete(task);
        }else{

            throw new NotFoundException();
        }

    }


    @Override
    public Task getOne(int id) throws NotFoundException {

        Optional<Task> pr = taskRepository.findById(id);

        if (pr.isPresent()) {
            return pr.get();
        }else{
            throw new NotFoundException();
        }

    }



    @Override
    public List<Task> getAllTasks() throws NotFoundException {

        List<Task> tasks = taskRepository.findAll();

        if(!tasks.isEmpty()){

            return tasks;
        }else{

            throw new NotFoundException();
        }



    }
    public List<Task> getAllTasksByListAndSprintName(String sprintName, String listName) throws NotFoundException{

        List<Task> tasks = taskRepository.getByTaskListAndSprintName(sprintName, listName);

        if(!tasks.isEmpty()){

            return tasks;
        }else{

            throw new NotFoundException();
        }

    }

    public List<Task> getAllTasksByTaskList(List<Integer> taskListIds) throws NotFoundException{



            List<Task> tasks = taskRepository.getByTaskListId(taskListIds);



        if(!tasks.isEmpty()){

            return tasks;
        }else{

            throw new NotFoundException();
        }


    }

    @Override
    public List<Task> getAllTasksByDate() throws BusinessException, NotFoundException {
        return FactoryDAO.getInstance().getTaskDAO().findAllByDate();
    }

    @Override
    public List<Task> getAllTasksByPriority() throws BusinessException, NotFoundException {

        return (List<Task>)FactoryDAO.getInstance().getTaskDAO().findAll().stream().sorted().collect(Collectors.toList());

    }

    @Override
    public List<Task> getAllTasksByListNameByDate(String list_name) throws BusinessException, NotFoundException {
        return FactoryDAO.getInstance().getTaskDAO().findAllByListNameByDate(list_name);
    }

    @Override
    public List<Task> getAllTasksByListNameByPriority(String list_name) throws BusinessException, NotFoundException {
        return (List<Task>)FactoryDAO.getInstance().getTaskDAO().findAllByListName(list_name).stream().sorted().collect(Collectors.toList());
    }


    @Override
    public List<Task> getAllTasksByListName(String list_name) throws BusinessException, NotFoundException {

        return FactoryDAO.getInstance().getTaskDAO().findAllByListName(list_name);
    }

}
