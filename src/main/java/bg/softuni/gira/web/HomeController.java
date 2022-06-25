package bg.softuni.gira.web;

import bg.softuni.gira.models.dto.TaskDto;
import bg.softuni.gira.service.TaskService;
import bg.softuni.gira.utils.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    private final TaskService taskService;
    private final CurrentUser currentUser;

    public HomeController(TaskService shipService, CurrentUser currentUser) {
        this.taskService = shipService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("productDto")
    public TaskDto productDto() {
        return new TaskDto();
    }

    @GetMapping("/")
    public String home(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "index";
        }

        model.addAttribute("tasks", taskService.getAllTasks());
        return "home";
    }
}
