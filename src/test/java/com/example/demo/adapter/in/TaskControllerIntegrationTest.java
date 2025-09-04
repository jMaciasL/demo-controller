package com.example.demo.adapter.in;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.demo.domain.Task;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateAndGetTask() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Task newTask = new Task();
        newTask.setDescription("Aprender Spring Boot");
        HttpEntity<Task> request = new HttpEntity<>(newTask, headers);
        ResponseEntity<Task> response = restTemplate
            .postForEntity("/tasks", request, Task.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Task created = response.getBody();
        assertNotNull(created);
        assertEquals(
            "Aprender Spring Boot",
            created.getDescription());
        Task fetched = restTemplate.getForObject("/tasks/" + created.getId(), Task.class);
        assertEquals(created.getId(), fetched.getId());
    }

    @Test
    void testMarkCompleted() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Task newTask = new Task();
        newTask.setDescription("Tarea a completar");
        HttpEntity<Task> request = new HttpEntity<>(newTask, headers);

        Task created = restTemplate.postForEntity("/tasks", request, Task.class).getBody();
        Task completed = restTemplate.postForObject("/tasks/" + created.getId() + "/complete", null, Task.class);

        assertTrue(completed.isCompleted());
    }
}
