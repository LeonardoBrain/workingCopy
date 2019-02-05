package ar.edu.iua.proyectoFinal.model.persistance;

import ar.edu.iua.proyectoFinal.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

    List<Task> getAllByCreationDate(String creationDate);


    @Query(value = "select * from task where task_list_id in ?1", nativeQuery = true)
    List<Task> getByTaskListId(List<Integer> taskListIds);

    @Query(value = "select * from task t inner join task_list tl on t.task_list_id = tl.task_list_id where sprint_name=?1 and tl.name =?2", nativeQuery = true)
    List<Task> getByTaskListAndSprintName(String sprintName, String taskListName);

    @Query(value = "select * from task t inner join task_list tl on t.task_list_id = tl.task_list_id where sprint_name=?1 and tl.name =?2 order by creation_date", nativeQuery = true)
    List<Task> getByTaskListAndSprintNameOrderByCreationDate(String sprintName, String taskListName);

    @Query(value = "select *, \n" +
            "case\n" +
            "when priority = 'High' then 2\n" +
            "when priority = 'Medium' then 1\n" +
            "when priority = 'Low' then 0\n" +
            "end as priorityNumber\n" +
            "from task t inner join task_list tl on t.task_list_id = tl.task_list_id where sprint_name=?1 and tl.name =?2 order by priorityNumber desc", nativeQuery = true)
    List<Task> getByTaskListAndSprintNameOrderByPriority(String sprintName, String taskListName);


}