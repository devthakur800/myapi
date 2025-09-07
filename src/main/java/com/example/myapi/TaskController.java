package com.example.myapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private int currentId = 1;

    // GET all tasks
    @GetMapping
    public List<Task> getTasks() {
        return tasks;
    }

    // POST create a new task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(currentId++);
        tasks.add(task);
        return task;
    }

    // GET a task by ID
    @GetMapping("/{id}")
    public Task getTask(@PathVariable int id) {
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // PUT update a task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setTitle(updatedTask.getTitle());
                t.setCompleted(updatedTask.isCompleted());
                return t;
            }
        }
        return null;
    }

    // DELETE a task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        tasks.removeIf(t -> t.getId() == id);
        return "Task deleted with id " + id;
    }
}

