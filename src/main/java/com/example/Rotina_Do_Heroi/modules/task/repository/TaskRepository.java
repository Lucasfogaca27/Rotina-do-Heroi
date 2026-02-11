package com.example.Rotina_Do_Heroi.modules.task.repository;

import com.example.Rotina_Do_Heroi.modules.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Busca todas as tarefas de um herói específico
    List<Task> findByUserId(Long userId);
}