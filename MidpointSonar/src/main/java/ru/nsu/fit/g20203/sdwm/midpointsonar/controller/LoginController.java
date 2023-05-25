package ru.nsu.fit.g20203.sdwm.midpointsonar.controller;

import com.evolveum.midpoint.client.api.exception.CommonException;
import com.evolveum.midpoint.client.api.exception.SchemaException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.ConnectionData;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPoint;

import java.io.FileNotFoundException;



@Controller
class LoginController {
    @GetMapping("/login")
    public String login(Model model) {
        ConnectionData conn = new ConnectionData();
        conn.setURL("http://localhost:8080/midpoint/ws/rest");
        conn.setLogin("administrator");
        conn.setPassword("5ecr3t");
        model.addAttribute("connectionData", conn);


        return "login";
    }
    /*
    Отсюда сохранить данные в нужные поля теста для создания соединения?
     */
    @PostMapping("/login")
    public String greetingSubmit(@ModelAttribute ConnectionData conn, Model model)  {
        try {
            MidPoint.init(conn.getURL(), conn.getLogin(), conn.getPassword());
        }
        catch (FileNotFoundException | SchemaException e) {
            e.printStackTrace();
            model.addAttribute("loginSuccess", false);
            model.addAttribute("loginError", true);
            return "login";
        }
        model.addAttribute("loginSuccess", true);
        model.addAttribute("loginError", false);
        return "login";
    }
}
