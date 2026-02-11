package com.example.Rotina_Do_Heroi.modules.task.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Long xpReward;
    private boolean completed = false;

    // Relacionamento: Muitas tarefas para um usuario
    private Long userId;

    // Getters e Setters Manuais
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getXpReward() { return xpReward; }
    public void setXpReward(Long xpReward) { this.xpReward = xpReward; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}