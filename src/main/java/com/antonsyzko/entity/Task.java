package com.antonsyzko.entity;

/**
 * Created by Admin on 16.09.2016.
 */

import org.hibernate.validator.constraints.Email;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "description should not  be  null ")
    @Size(min = 1, max = 64,message = " DESCRIPTION HAS TO BE BETWEEN 1 AND 64 SYMBOLS ")
    @Column(name = "description")
    private String description;

    @Column(name = "completed")
    private boolean completed;

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd-MMM-YYYY")
    private DateTime createdAt;

    @Column
    @NotNull(message = "date has to be  picked")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd-MMM-YYYY")
    @Future(message = " SET A DATE IN FUTURE")

    private DateTime dueTo;

    public DateTime getDueTo() {
        return dueTo;
    }

    public void setDueTo(DateTime dueTo) {
        this.dueTo = dueTo;
    }

    //indu
     @NotNull(message = "priority should not  be  null ")
     @Size(min = 1, max = 30,message = " task priority has to be between 1 and 30 symbols long ")
    @Column(name="task_priority")
    private String taskPriority;

    @NotNull(message = "status should not  be  null ")
    @Size(min = 1, max = 30,message = " task status has to be between 1 and 30 symbols long ")
    @Column(name="task_status")
    private String taskStatus;

//    @Column(name="task_archived")
//    private int taskArchived = 0;

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

//    public int getTaskArchived() {
//        return taskArchived;
//    }
//
//    public void setTaskArchived(int taskArchived) {
//        this.taskArchived = taskArchived;
//    }

//indu


    //21

    @NotNull(message = "email should not  be  null ")
    @Size(min = 1, max = 64,message = " email  has to be between 1 and 64 symbols long ")
    @Column(name="email")
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    //21

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }



    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("description='").append(description).append('\'');
        sb.append(", completed=").append(completed);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", taskPriority='").append(taskPriority).append('\'');
        sb.append(", taskStatus='").append(taskStatus).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }


}