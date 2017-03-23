package main.java.config;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.springframework.stereotype.Component;

@Component
public class GreetingBean {
    public void print(){
        System.out.println("Het doet het");
    }
}
