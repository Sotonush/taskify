package com.example.taskify.services;

import com.example.taskify.entity.Task;
import com.example.taskify.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> allTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public boolean deleteTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if(taskOptional.isPresent()) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Task updeteTask(Task task, Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task1 = taskOptional.get();
            task1.setTitle(task.getTitle());
            task1.setDescription(task.getDescription());
            task1.setStatus(task.getStatus());
            return taskRepository.save(task1);
        }
        return null;
    }

}
