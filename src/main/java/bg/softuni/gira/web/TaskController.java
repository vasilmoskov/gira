package bg.softuni.gira.web;

import bg.softuni.gira.models.dto.TaskDto;
import bg.softuni.gira.service.TaskService;
import bg.softuni.gira.utils.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class TaskController {
    private final CurrentUser currentUser;
    private final TaskService taskService;

    public TaskController(CurrentUser currentUser, TaskService shipService) {
        this.currentUser = currentUser;
        this.taskService = shipService;
    }

    @GetMapping("/tasks/add")
    public String addProduct() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/login";
        }

        return "add-task";
    }

    @ModelAttribute("taskDto")
    public TaskDto taskDto() {
        return new TaskDto();
    }

    @PostMapping("/tasks/add")
    public String addProduct(@Valid TaskDto taskDto, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("taskDto", taskDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.taskDto", bindingResult);
            return "redirect:add";
        }

        taskService.add(taskDto);

        return "redirect:/";
    }

    @GetMapping("tasks/progress/{id}")
    public String progress(@PathVariable long id) {
        taskService.progress(id);
        return "redirect:/";
    }
}
