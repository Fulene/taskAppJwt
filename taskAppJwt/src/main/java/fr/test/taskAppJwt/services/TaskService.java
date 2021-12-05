package fr.test.taskAppJwt.services;

import fr.test.taskAppJwt.model.dao.TaskRepository;
import fr.test.taskAppJwt.model.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void mockTasks() {
        Stream.of("T1", "T2", "T3").forEach(t -> {
            taskRepository.save(new Task(null, t));
        });
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

}
