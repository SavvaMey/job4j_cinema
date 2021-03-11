package ru.job4j.servlet;

import ru.job4j.model.Account;
import ru.job4j.model.HallPlace;
import ru.job4j.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] array = req.getParameterValues("place");
        HttpSession sc = req.getSession();
        sc.setAttribute("places", req.getParameterValues("place"));
        Collection<HallPlace> hallPlaces = PsqlStore.instOf().getReservedPlaces(array);
        req.setAttribute("hallPlaces", hallPlaces);
        req.getRequestDispatcher("payment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession sc = req.getSession();
        String[] array = (String[]) sc.getAttribute("places");
        String fio = req.getParameter("username");
        String phone = req.getParameter("phone");
        Account acc = PsqlStore.instOf().findAccount(phone);
        if (acc == null) {
            acc = PsqlStore.instOf().saveUser(new Account(0, fio, phone));
        }
        PsqlStore.instOf().updatePlacesStore(acc, array);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
