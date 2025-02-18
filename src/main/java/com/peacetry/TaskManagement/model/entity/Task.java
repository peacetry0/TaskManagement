package com.peacetry.TaskManagement.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "tasks")
@SQLRestriction("deleted_at IS NULL ")
@SQLDelete(sql = "UPDATE tasks SET deleted_at = NOW() WHERE id = ?")
public class Task extends BaseEntity{

    @Column(name = "title", nullable = false)
    private String title ;

    @Column(name = "description",nullable = false)
    private String description ;

    @Column(name = "completed",nullable = false)
    private Boolean completed = false;

    public Task() {
    }

    public Task(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


}
