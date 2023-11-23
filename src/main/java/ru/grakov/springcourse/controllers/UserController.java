package ru.grakov.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.grakov.springcourse.models.User;
import ru.grakov.springcourse.service.UserService;

@Controller
@RequestMapping("/people")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", userService.index());
        return "people/index";
    }

//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userDAO.show(id));
//        return "people/show";
//    }
    @GetMapping("/")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        userService.save(user);
        return "redirect:/people";
    }

    //    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("user", userDAO.show(id));
//        return "people/edit";
//    }
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.show(id));
        return "people/edit";
    }

//        @PatchMapping("/{id}")
//    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
//                         @PathVariable("id") int id) {
//        if (bindingResult.hasErrors())
//            return "people/edit";
//
//        userDAO.update(id, user);
//        return "redirect:/people";
//    }
    @PatchMapping
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @RequestParam("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        userService.update(id, user);
        return "redirect:/people";
    }

    @DeleteMapping
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/people";
    }
}