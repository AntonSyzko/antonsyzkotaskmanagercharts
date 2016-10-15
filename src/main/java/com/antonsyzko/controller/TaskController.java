package com.antonsyzko.controller;
/**
 * Created by Admin on 16.09.2016.
 */
import com.antonsyzko.entity.Task;
import com.antonsyzko.repository.TaskRepository;
import com.antonsyzko.service.NotificationService;
import com.antonsyzko.service.TaskNotFoundException;
import com.antonsyzko.service.TaskServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
@EnableWebSecurity
public class TaskController extends WebMvcConfigurerAdapter {
    Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private TaskRepository repository;
    @Autowired
    private TaskServiceImpl service;
    @Autowired
    private NotificationService notificationService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value="/charts",method=RequestMethod.GET)
    public String developersList(Model model) {
        return "charts";
    }

    @RequestMapping(value="/rombspolars",method=RequestMethod.GET)
    public String rombsPolars(Model model) {
        return "rombspolars";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        model.addAttribute("tasks", service.findAll());
        model.addAttribute("newTask", new Task());
        return "tasks";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestParam Long id, @Validated Task task) {
        service.update(id, task);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String delete(@RequestParam Long id,RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("deleted"," Task with ID number :   " + id + " has  been deleted ") ;
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(@Valid @ModelAttribute("newTask") Task task, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("newTask", task);
            model.addAttribute("tasks", service.findAll());
            return "tasks";
        } else {
            service.create(task);
            try {
               notificationService.sendNotificaitoin(task);
               redirectAttributes.addFlashAttribute("message",
                        "TASK HAS BEEN SUCCESSFULLY SENT TO      " + task.getEmail()) ;
            }catch( Exception e ){
               logger.info("Error Sending Email: " + e.getMessage());
               redirectAttributes.addFlashAttribute("failed",
                        " delivery to  " + task.getEmail() + " failed" + e.getMessage()) ;
            }
            return "redirect:/";
        }
    }

    @ModelAttribute("taskStatus")
    public List<String> statusList() {
        List<String> list = new ArrayList<String>();
        list.add("DONE");
        list.add("IN PROCESS");
        list.add("STARTED");
        return list;
    }

    @ModelAttribute("taskPriority")
    public List<String> priorityList() {
        List<String> list = new ArrayList<String>();
        list.add("LOW");
        list.add("MEDIUM");
        list.add("HIGH");
        return list;
    }
    @ModelAttribute("email")
    public List<String> emailList() {
        List<String> list = new ArrayList<String>();
        list.add("antonio.shizko@gmail.com");
        list.add("java_dev@gmail.com");
        list.add("team_lead@gmail.com");
        list.add("project_manager@gmail.com");
        list.add("antonsyzko@gmail.com");
        return list;
    }
    
    @ModelAttribute("memail")
    public int meMailList(){
        int  result= repository.findByEmail("antonio.shizko@gmail.com").size();
        return result;
    }
    
    @ModelAttribute("testerqa")
    public int testerQAList(){
        int  result= repository.findByEmail("antonsyzko@gmail.com").size();
        return result;
    }
    
    @ModelAttribute("javadevmail")
    public int javaDevMailList(){
        int  result= repository.findByEmail("java_dev@gmail.com").size();
        return result;
    }
    
    @ModelAttribute("teamleadmail")
    public int teamLeadMailList(){
        int  result= repository.findByEmail("team_lead@gmail.com").size();
        return result;
    }
    @ModelAttribute("pmmail")
    public int ProjManagerMailList(){
        int  result= repository.findByEmail("project_manager@gmail.com").size();
        return result;
    }

    @ModelAttribute("highpriority")
    public int highPriorutyList(){
     int  result= repository.findByTaskPriority("HIGH").size();
        return result;
    }
    
    @ModelAttribute("lowpriority")
    public int lowPriorityList(){
        int  result= repository.findByTaskPriority("LOW").size();
        return result;
    }
    
    @ModelAttribute("mediumpriority")
    public int mediumPriorityList(){
        int  result= repository.findByTaskPriority("MEDIUM").size();
        return result;
    }
    
    @ModelAttribute("startedstatus")
    public int startedStatusList(){
        int  result= repository.findByTaskStatus("STARTED").size();
        return result;
    }
    
    @ModelAttribute("donestatus")
    public int doneStatusList(){
        int  result= repository.findByTaskStatus("DONE").size();
        return result;
    }
    
    @ModelAttribute("inprocessstatus")
    public int inProcessStatusList(){
        int  result= repository.findByTaskStatus("IN PROCESS").size();
        return result;
    }
    
    @ModelAttribute("statall")
    public int statisticsList(){
        int  result= repository.findAll().size();
        return result;
    }

    @ExceptionHandler({TaskNotFoundException.class})
    public ModelAndView getSuperheroesUnavailable(TaskNotFoundException ex) {
        return new ModelAndView("error", "error", ex.getMessage());
    }

}
