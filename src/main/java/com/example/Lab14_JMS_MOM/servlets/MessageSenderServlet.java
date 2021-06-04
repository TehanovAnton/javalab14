package com.example.Lab14_JMS_MOM.servlets;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MessageSenderServlet", value = "/MessageSenderServlet")
public class MessageSenderServlet extends HttpServlet {
    @Resource(lookup = "DefaultJMSConnectionFactory")
        ConnectionFactory factory;

    @Resource(lookup = "BankOderDestination")
        Destination cardsQueue;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
