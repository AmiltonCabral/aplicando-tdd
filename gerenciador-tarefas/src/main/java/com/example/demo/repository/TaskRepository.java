package com.example.demo.repository;

import com.example.demo.domain.Task;
import com.example.demo.dto.TaskDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT T FROM Task AS T WHERE T.user.id = ?1")
    public List<Task> findAllByUserId(Long userId, Sort sort);

}
