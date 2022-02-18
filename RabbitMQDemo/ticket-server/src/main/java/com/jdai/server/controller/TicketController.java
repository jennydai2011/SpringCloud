package com.jdai.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TicketController {

    @GetMapping("/ticket")
    public String buyTicket(HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
        Thread.sleep(200);
        return "Ticket bought...";
    }

}
