package ru.zharinov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zharinov.dao.PersonDao;
import ru.zharinov.models.Person;

@Controller
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {
    private final PersonDao personDao;

    @GetMapping()
    public String showPeople(Model model) {
        System.out.println(personDao.getAllPeople());
        model.addAttribute("people", personDao.getAllPeople());
        return "people/show-people";
    }

    @GetMapping("/new")
    public String newPeople(@ModelAttribute("person") Person person) {
        return "people/new-person";
    }

    @PostMapping
    public String savePerson(@ModelAttribute("person") Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new-person";
        }
        personDao.savePerson(person);
        return "redirect:/people";
    }
}
