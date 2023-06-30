package com.todolist.todolistproject.repository;

import com.todolist.todolistproject.model.Task;
import com.todolist.todolistproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {
    /**
     * FindAllAddressesByUser() finds all addresses by User from DB
     */
    @Query(value = "SELECT o FROM Task o WHERE o.user.id = :id")
    List<Task> findAllTasksByUser(@Param("id") long id) ;

}
