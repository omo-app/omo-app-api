package com.findev.omo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class HealthCheck {

  @GetMapping("api/_hcheck")
  public String healthCheck(){
    return "hello artku";
  }

  @GetMapping("blockedapi/_hcheck")
  public String blockedHealthCheck(){
    return "block artku";
  }
}
