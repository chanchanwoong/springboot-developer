package me.chanwoong.springbootdeveloper.controller;

import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {

    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model) {
        Person examplePerson = Person.builder()
                .id(1L)
                .name("박찬웅")
                .age(26)
                .hobbies(List.of("운동", "노래"))
                .build();

        model.addAttribute("person", examplePerson);
        model.addAttribute("today", LocalDate.now());

        return "example";
    }

    @Getter
    @Builder
    static class Person {
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
