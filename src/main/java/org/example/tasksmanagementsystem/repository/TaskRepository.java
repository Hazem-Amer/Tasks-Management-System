package org.example.tasksmanagementsystem.repository;

import org.example.tasksmanagementsystem.model.Task;
import org.example.tasksmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByOwner(User owner);

}
