
package fr.iut.aluilcine.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController qui permet de test l'application sans dependance a MongoDB.
 */
@RestController
public class HelloController {

    @Value("${env.NAME}")
    String name;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/hello")
    public String helloPathVariable(@RequestBody String name) {
        return String.format("hello - %s", name);
    }

    @GetMapping("/hello/env")
    public String helloWithEnv() {
        return String.format("hello - %s", name);
    }
}