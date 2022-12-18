package ru.fomina.recipes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FirstController {

    @GetMapping
    public String runApp() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String description(){
        return "Вы находитесь на странице приложения с рецептами." +
                " Проект создан 15 декабря 2022 года." +
                " В рамках проекта будет создано веб-приложение с рецептами приготовления блюд." +
                " Проект написан на языке программирования Java." +
                " Он реализуется с помощью среды разработки IntelliJ IDEA, фреймворка Spring и сборщика проектов Maven." +
                " Автор проекта — Фомина Марина";
    }
}
