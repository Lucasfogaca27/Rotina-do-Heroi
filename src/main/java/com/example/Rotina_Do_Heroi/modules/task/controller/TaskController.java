package com.example.Rotina_Do_Heroi.modules.task.controller;

import com.example.Rotina_Do_Heroi.modules.task.entity.Task;
import com.example.Rotina_Do_Heroi.modules.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*") // Para o seu React não ter erro de CORS
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Criar uma nova tarefa para o herói
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    // Listar todas as tarefas de um herói específico (ex: ID 1)
    @GetMapping("/hero/{userId}")
    public ResponseEntity<List<Task>> getByHero(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksByHero(userId));
    }

    // O ENDPOINT ÉPICO: Concluir tarefa e ganhar XP
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> complete(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.completeTask(id));
    }
}