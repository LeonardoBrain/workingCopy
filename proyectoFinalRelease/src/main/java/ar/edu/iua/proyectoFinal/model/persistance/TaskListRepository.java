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
    //select *,
    //case
    //when name = 'Backlog' then 1
    //
    //when name = 'TODO' then 2
    //
    //when name = 'In Progress' then 3
    //
    //when name = 'Waiting' then 4
    //
    //when name = 'Done' then 5
    //end as sortedLists
    //from task_list
    //where sprint_name = 'Sprint1'
    //order by sortedLists asc
    @Query(value = "select *,\n" +
            "    case\n" +
            "    when name = 'Backlog' then 1\n" +
            "    \n" +
            "    when name = 'TODO' then 2\n" +
            "    \n" +
            "    when name = 'In Progress' then 3\n" +
            "    \n" +
            "    when name = 'Waiting' then 4\n" +
            "    \n" +
            "    when name = 'Done' then 5\n" +
            "    end as sortedLists\n" +
            "    from task_list\n" +
            "    where sprint_name = ?1\n" +
            "    order by sortedLists asc", nativeQuery = true)
    List<TaskList> findBySprintName(String sprintName);

    @Query(value = "select distinct sprint_name from task_list where sprint_name!='Backlog'", nativeQuery = true)
    List<String> findAllSprints();


    @Query(value = "select distinct task_list_id from task_list where name=?1 and sprint_name =?2", nativeQuery = true)
    int getTaskListIdByNameAndSprintName(String taskListName, String sprintName);

}
