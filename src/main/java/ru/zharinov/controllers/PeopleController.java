package ru.zharinov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zharinov.dao.PersonDao;
import ru.zharinov.models.Person;

@Controller
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {
    private final PersonDao personDao;

    @GetMapping()
    public String showPeople(Model model) {
        model.addAttribute("people", personDao.getAllPeople());
        return "people/show-people";
    }

    @GetMapping("/new")
    public String newPeople(@ModelAttribute("person") Person person) {
        return "people/new-person";
    }

    @PostMapping()
    public String savePerson(@ModelAttribute("person") Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new-person";
        }
        personDao.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String infoPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.getPersonById(id));
        model.addAttribute("books", personDao.getPersonWithBooks(id));
        return "people/show-person";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDao.deletePersonById(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String showPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.getPersonById(id));
        return "people/edit-person";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") Person person, BindingResult bindingResult,
                               @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "people/edit-person";
        }
        personDao.updatePersonById(id, person);
        return "redirect:/people";
    }
}
