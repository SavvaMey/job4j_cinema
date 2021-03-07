package ru.job4j.servlet;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.model.HallPlace;
import ru.job4j.store.PsqlStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<HallPlace> places = PsqlStore.instOf().getAllPlaces();
        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter writer = new PrintWriter(resp.getOutputStream(), true, StandardCharsets.UTF_8);
        JSONArray ar = new JSONArray();
        for (HallPlace place :  places) {
            JSONObject json = new JSONObject();
            json.put("idPlace", place.getId());
            json.put("row", place.getRow());
            json.put("column", place.getCol());
            json.put("status", place.isStatus());
            ar.put(json);
        }
        writer.println(ar);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
       if ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        System.out.println(data);
        req.setAttribute("reserved", data);
        resp.sendRedirect(req.getContextPath() + "/payment"); // - не работает переходит в сервлет и страницу не обновляет
        req.getRequestDispatcher("/payment.jsp").forward(req, resp); // - не работает
    }
}
