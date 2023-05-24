package ru.nsu.fit.g20203.sdwm.midpointsonar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.ConnectionData;


@Controller
class LoginController {
    @GetMapping("/login")
    public String login(Model model) {
        ConnectionData conn = new ConnectionData();
        conn.setURL("localhost:8080");
        conn.setLogin("administrator");
        conn.setPassword("5ecr3t");
        model.addAttribute("connectionData", conn);
        return "login";
    }
    /*
     Отсюда сохранить данные в нужные поля теста для создания соединения?
     */
    @PostMapping("/login")
    public String greetingSubmit(@ModelAttribute ConnectionData conn) {
        System.out.println(conn.getLogin());
        System.out.println(conn.getPassword());
        return "thanks";
    }
}
