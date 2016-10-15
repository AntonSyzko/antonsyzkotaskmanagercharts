package com.antonsyzko.repository;
/**
 * Created by Admin on 16.09.2016.
 */
import com.antonsyzko.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findByTaskPriority(String taskPriority);
    public List<Task> findByTaskStatus(String taskStatus);
    public List<Task> findByEmail(String email);
    public List<Task> findByCompleted(boolean completed);

}
