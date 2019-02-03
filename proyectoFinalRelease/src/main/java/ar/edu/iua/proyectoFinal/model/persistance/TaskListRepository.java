package ar.edu.iua.proyectoFinal.model.persistance;

import ar.edu.iua.proyectoFinal.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskListRepository extends JpaRepository<TaskList,Integer> {

    TaskList findByName(String name);
    TaskList findByNameAndSprintName(String name, String sprintName);
    List<TaskList> findBySprintName(String sprintName);

    @Query(value = "select distinct sprint_name from task_list where sprint_name!='Backlog'", nativeQuery = true)
    List<String> findAllSprints();


}
