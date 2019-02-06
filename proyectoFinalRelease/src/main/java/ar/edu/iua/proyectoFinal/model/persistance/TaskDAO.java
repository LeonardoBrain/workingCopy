package ar.edu.iua.proyectoFinal.model.persistance;


import ar.edu.iua.proyectoFinal.model.Task;
import ar.edu.iua.proyectoFinal.model.TaskList;
import ar.edu.iua.proyectoFinal.model.exception.BusinessException;
import ar.edu.iua.proyectoFinal.model.exception.NotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDAO implements IGenericDAO<Task, Serializable> {

    private static TaskDAO instance = null;
    @Autowired
    TaskListRepository taskListRepository;

    @Autowired
    private EntityManagerFactory emf;

    public TaskDAO() {

    }
    @Bean
    public static TaskDAO getInstance() {
        if (instance == null)
            instance = new TaskDAO();

        return instance;
    }
    @Override
    public Task save(Task object) {


        Session session = emf.unwrap(SessionFactory.class).openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(object);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null)
                tx.rollback();
            throw e;
        } finally {
            session.close();
        }


        return object;
    }

    @Override
    public Task findById(int id) throws NotFoundException{
        Session session = emf.unwrap(SessionFactory.class).openSession();
        Transaction tx = null;
        Task task = null;

        try {
            tx = session.beginTransaction();

            task = session.get(Task.class, id);

            if(task==null){
                throw new NotFoundException();
            }

            tx.commit();

            return task;

        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return null;




    }
    @Override
    public List<Task> findAll() {
        Session session = emf.unwrap(SessionFactory.class).openSession();
        Transaction tx = null;
        List<Task> tasks = new ArrayList<Task>();

        try {
            tx = session.beginTransaction();

            session.flush();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Task> query = builder.createQuery(Task.class);
            Root<Task> from = query.from(Task.class);

            query.select(from);
            tasks = session.createQuery(query).getResultList();
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

        return tasks;
    }
    @Override
    public List<Task> findAllByDate() {
        Session session = emf.unwrap(SessionFactory.class).openSession();
        Transaction tx = null;
        List<Task> tasks = new ArrayList<Task>();

        try {
            tx = session.beginTransaction();

            session.flush();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Task> query = builder.createQuery(Task.class);
            Root<Task> from = query.from(Task.class);

            query.select(from);
            query.orderBy(builder.asc(from.get(("creationDate"))));
            tasks = session.createQuery(query).getResultList();
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

        return tasks;
    }

    @Override
    public List<Task> findAllByPriority() {
        Session session = emf.unwrap(SessionFactory.class).openSession();
        Transaction tx = null;
        List<Task> tasks = new ArrayList<Task>();

        try {
            tx = session.beginTransaction();

            session.flush();
            CriteriaBuilder builder = session.getCriteriaBuilder();


            CriteriaQuery<Task> query = builder.createQuery(Task.class);
            Root<Task> from = query.from(Task.class);
            query.multiselect(
                    from.get("name"),from.get("creationDate"),from.get("modificactionDate"),from.get("estimatedTime"),from.get("taskList"),
                    builder.selectCase()
                            .when(builder.equal(from.get("priority"), "high"), "1")
                            .when(builder.equal(from.get("priority"), "medium"), "2")
                            .when(builder.equal(from.get("priority"), "low"), "3")
                            .otherwise("4").alias("priority")).orderBy(builder.asc(from.get("priority")));

            tasks = session.createQuery(query).getResultList();

            System.out.println("Tareas");
            for (Task task:
                 tasks) {
                System.out.println("Task:"+task.getName());
            }
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

        return tasks;
    }

    @Override
    public List<Task> findAllByListName(String name) throws BusinessException {
        Session session = emf.unwrap(SessionFactory.class).openSession();
        Transaction tx = null;
        List<Task> tasks = new ArrayList<Task>();

        TaskList taskList=taskListRepository.findByName(name);

        if(taskList==null)
            throw new BusinessException();

            try {
                tx = session.beginTransaction();

                session.flush();
                CriteriaBuilder builder = session.getCriteriaBuilder();

                CriteriaQuery<Task> query = builder.createQuery(Task.class);
                Root<Task> from = query.from(Task.class);
                Join<Task, TaskList> join = from.join("taskList");

                query.select(from).where(builder.equal(join.get("name"), name));

                tasks = session.createQuery(query).getResultList();
                tx.commit();

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
            } finally {
                session.close();
            }

        return tasks;
        }




    @Override
    public List<Task> findAllByListNameByDate(String name) {
        Session session = emf.unwrap(SessionFactory.class).openSession();
        Transaction tx = null;
        List<Task> tasks = new ArrayList<Task>();

        try {
            tx = session.beginTransaction();

            session.flush();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Task> query = builder.createQuery(Task.class);
            Root<Task> from = query.from(Task.class);
            Join<Task, TaskList> join = from.join("taskList");

            query.select(from).where(builder.equal(join.get("name"),name));
            query.orderBy(builder.asc(from.get(("creationDate"))));

            tasks = session.createQuery(query).getResultList();
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

        return tasks;
    }

    @Override
    public List<Task> findAllByListNameByPriority(String name) {
        Session session = emf.unwrap(SessionFactory.class).openSession();
        Transaction tx = null;
        List<Task> tasks = new ArrayList<Task>();

        try {
            tx = session.beginTransaction();

            session.flush();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Task> query = builder.createQuery(Task.class);
            Root<Task> from = query.from(Task.class);
            Join<Task, TaskList> join = from.join("taskList");

            query.select(from).where(builder.equal(join.get("name"),name));
            query.orderBy(builder.asc(from.get(("priority"))));

            tasks = session.createQuery(query).getResultList();
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

        return tasks;
    }
    @Override
    public void delete(Task object) {
        Session session = emf.unwrap(SessionFactory.class).openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Task task = session.get(Task.class, object.getTaskId());
            if (task != null) {
                session.delete(task);
            }else {
                throw new NotFoundException();
            }

            tx.commit();

        } catch (HibernateException | NotFoundException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @Override
    public void update(Task object) {
        System.out.println("Entroooo");
        Session session = emf.unwrap(SessionFactory.class).openSession();
        Transaction tx = null;
        //Task task = session.get(Task.class, object.getTaskId());
        //System.out.println("Name"+ task.getName());




        try {
            tx = session.beginTransaction();
            System.out.println("Entraaa4 "+ object.getName());

            //TaskList taskList= taskListRepository.findByName(object.getTaskList().getName());
           // task.setTaskList(taskList);
            //object.setModificactionDate(object.getModificactionDate());

            session.update(object);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                System.out.println("Entraaa555");

                tx.rollback();
            }
        } finally {
            session.close();
        }

    }




}
