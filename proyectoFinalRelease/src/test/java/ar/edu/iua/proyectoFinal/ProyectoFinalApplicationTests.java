package ar.edu.iua.proyectoFinal;

import ar.edu.iua.proyectoFinal.business.TaskListBusinessI;
import ar.edu.iua.proyectoFinal.business.implementation.TaskBusiness;
import ar.edu.iua.proyectoFinal.business.implementation.TaskListBusiness;
import ar.edu.iua.proyectoFinal.controller.TaskRestController;
import ar.edu.iua.proyectoFinal.model.Task;
import ar.edu.iua.proyectoFinal.model.TaskList;
import ar.edu.iua.proyectoFinal.model.exception.BusinessException;
import ar.edu.iua.proyectoFinal.model.exception.NotFoundException;
import ar.edu.iua.proyectoFinal.utils.UtilFunctions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProyectoFinalApplicationTests {

    @Autowired
    TaskListBusinessI taskListBusiness;
    @Autowired
    TaskBusiness taskBusiness;

	@Test
	public void getAllTaskSuccess() throws NotFoundException, BusinessException {

		List<Task> resultList = (List<Task>) taskBusiness.getAllTasks();

		assertEquals( "TT", resultList.get(0).getName());
		assertEquals( "aa", resultList.get(1).getName());


	}

	@Test
	public void getAllTaskByListNameSuccess() throws NotFoundException, BusinessException {

		List<Task> resultList = (List<Task>) taskBusiness.getAllTasksByListName("Backlog");

		assertEquals( "EE", resultList.get(0).getName());



	}
	@Test
	public void getAllTaskByListNameByPrioritySuccess() throws NotFoundException, BusinessException {

		List<Task> resultList = (List<Task>) taskBusiness.getAllTasksByListNameByPriority("TODO");

		assertEquals( "TT", resultList.get(0).getName());
		assertEquals( "LL", resultList.get(1).getName());


	}
	@Test
	public void getOneSuccess() throws NotFoundException, BusinessException {

		Task result =  taskBusiness.getOne(14);

		assertEquals( "PP", result.getName());



	}

	@Test
	public void getAllTaskByPrioritySuccess() throws NotFoundException, BusinessException {


		List<Task> result = (List<Task>) taskBusiness.getAllTasksByPriority();

		assertEquals( "EE", result.get(1).getName());



	}

	@Test
	public void addTaskSuccess() throws NotFoundException, BusinessException {


		TaskList taskList = taskListBusiness.getOne(1);
		UtilFunctions ul = new UtilFunctions();

		Task originalTask = new Task("Test Task",ul.getCurrentTimeUsingDate(), ul.getCurrentTimeUsingDate(), 50.0, taskList, "Medium");

		Task resultTask = taskBusiness.add(originalTask,true);

		assertEquals(originalTask,resultTask);
	}

}

