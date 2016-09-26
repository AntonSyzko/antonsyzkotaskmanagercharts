package com.antonsyzko.service;

/**
 * Created by Admin on 16.09.2016.
 */





import com.antonsyzko.entity.Task;
import com.antonsyzko.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl {
    private Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);


    @Autowired
    private TaskRepository repository;


    @Autowired
    private NotificationService notificationService;

    public List<Task> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Task create(Task task) {
        Task newTask = new Task();
        newTask.setDescription(task.getDescription());
        newTask.setCompleted(task.isCompleted());
        newTask.setCreatedAt(new Date());
        newTask.setTaskPriority(task.getTaskPriority());
        newTask.setTaskStatus(task.getTaskStatus());
        newTask.setEmail(task.getEmail());
        newTask.setDueTo(task.getDueTo());
//
//        try {
//            notificationService.sendNotificaitoin(task);
//
//
//
//        }catch( Exception e ){
//            // catch error
//            logger.info("Error Sending Email: " + e.getMessage());
//
//        }

        return repository.saveAndFlush(newTask);
    }


//    //21
//    @Transactional
//    public Task updateCompleted(Long id,Task task){
//        Task entity = findOneSafe(id);
//        entity.setCompleted(task.isCompleted());
//        return repository.saveAndFlush(entity);
//    }
//    //21

    @Transactional
    public Task update(Long id, Task task) {
        Task entity = findOneSafe(id);
        entity.setDescription(task.getDescription());
        entity.setCompleted(task.isCompleted());
        entity.setTaskStatus(task.getTaskStatus());
        entity.setTaskPriority(task.getTaskPriority());
        entity.setEmail(task.getEmail());

        entity.setDueTo(task.getDueTo());
//        return entity;
        return repository.saveAndFlush(entity);
    }

    @Transactional
    public void delete(Long id) {
        Task task = findOneSafe(id);
        repository.delete(task);
    }

    private Task findOneSafe(Long id) {
        Task task = repository.findOne(id);
        if (task == null) {
            throw new TaskNotFoundException("not found ");
        } else {
            return task;
        }
    }










}