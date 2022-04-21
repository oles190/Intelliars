package com.intelliarts.test.controller;

import com.intelliarts.test.dto.CategoryDTO;
import com.intelliarts.test.repository.CategoryRepository;
import com.intelliarts.test.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("mvc")
@AllArgsConstructor
public class ControllerM {

    CategoryService categoryService;
    CategoryRepository categoryRepository;

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
        response.sendRedirect("/mvc/allCategories");
    }
}
//testing