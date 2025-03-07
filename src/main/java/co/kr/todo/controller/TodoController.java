package co.kr.todo.controller;

import co.kr.todo.Todo;
import co.kr.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoRepository.findAllByOrderByPinnedDescPriorityAscIdAsc());
        List<String> priorities = Arrays.asList("HIGH", "MEDIUM", "LOW");
        model.addAttribute("priorities", priorities);
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute Todo todo) {
        if (todo.getPriority() == null) todo.setPriority("MEDIUM");
        todoRepository.save(todo);
        return "redirect:/";
    }

    @PostMapping("/toggle/{id}")
    public String toggleTodo(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setCompleted(!todo.isCompleted());
        todoRepository.save(todo);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editTodoForm(@PathVariable Long id, Model model) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        model.addAttribute("todo", todo);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateTodo(@PathVariable Long id, @ModelAttribute Todo todo) {
        todo.setId(id);
        todoRepository.save(todo);
        return "redirect:/";
    }

    @PostMapping("/pin/{id}")
    public String pinTodo(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setPinned(!todo.isPinned());
        todoRepository.save(todo);
        return "redirect:/";
    }

    // About 페이지 복구
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}