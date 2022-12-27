package ru.fomina.recipes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "Вводные данные о проекте")
public class FirstController {

    @GetMapping
    @Operation(summary = "Проверка запуска приложения")
    public String runApp() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    @Operation(summary = "Описание проекта")
    public String description(){
        return "Вы находитесь на странице приложения с рецептами." +
                " Проект создан 15 декабря 2022 года." +
                " В рамках проекта будет создано веб-приложение с рецептами приготовления блюд." +
                " Проект написан на языке программирования Java." +
                " Он реализуется с помощью среды разработки IntelliJ IDEA, фреймворка Spring и сборщика проектов Maven." +
                " Автор проекта — Фомина Марина";
    }
}
