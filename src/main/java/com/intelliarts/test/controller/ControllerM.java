package com.intelliarts.test.controller;

import com.intelliarts.test.dto.CategoryDTO;
import com.intelliarts.test.repository.CategoryRepository;
import com.intelliarts.test.service.CategoryService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("mvc")
public class ControllerM {

    public ControllerM(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    CategoryService categoryService;
    CategoryRepository categoryRepository;

    @GetMapping("creates")
    public  String cr(){
        return "create";
    }

    @SneakyThrows
    @PostMapping("/create")
    public void create(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int items = Integer.parseInt(request.getParameter("items"));

        if (categoryRepository.getCategoryByName(name) != null) {
            throw new RuntimeException("Already exist!");
        }
        categoryService.addCategory(new CategoryDTO(price, name, items));
        List<CategoryDTO> list = categoryService.getAll();
        session.setAttribute("list", list);
        response.sendRedirect("/mvc/second");
    }

    @GetMapping("/start")
    public String test2() {
        return "/WEB-INF/pages/first.jsp";
    }

    @GetMapping("/second")
    public String second() {
        return "/WEB-INF/pages/second.jsp";
    }

    @SneakyThrows
    @PostMapping("/index")
    public void test(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        List<CategoryDTO> list = categoryService.getAll();
        session.setAttribute("list", list);
        //   request.getServletContext().getRequestDispatcher("/second.jsp").forward(request, response);
        response.sendRedirect("/mvc/second");
    }
}
