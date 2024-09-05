package ru.zharinov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zharinov.dao.PersonDao;

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
}
