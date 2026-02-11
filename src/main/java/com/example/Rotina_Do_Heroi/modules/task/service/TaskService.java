package com.example.Rotina_Do_Heroi.modules.task.service;

import com.example.Rotina_Do_Heroi.modules.task.entity.Task;
import com.example.Rotina_Do_Heroi.modules.task.repository.TaskRepository;
import com.example.Rotina_Do_Heroi.modules.user.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService; // Injeção do serviço de usuário para integração

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    // Criar uma nova missão para o herói
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // O coração da integração: Concluir tarefa e ganhar XP
    public Task completeTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Missão não encontrada!"));

        if (!task.isCompleted()) {
            task.setCompleted(true);

            // Chama a lógica de XP que você já validou no UserService
            userService.gainXp(task.getUserId(), task.getXpReward());

            return taskRepository.save(task);
        }

        throw new RuntimeException("Esta missão já foi concluída!");
    }

    public List<Task> getTasksByHero(Long userId) {
        return taskRepository.findByUserId(userId);
    }
}