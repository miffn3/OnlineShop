package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.CategoryCreateRequestDto;
import net.thumbtack.onlineshop.dto.request.EditCategoryRequestDto;
import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.OnlineShopException;
import net.thumbtack.onlineshop.service.iface.CategoryService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoriesController {
    private final CategoryService categoryService;
    private final SessionService sessionService;

    public CategoriesController(CategoryService categoryService, SessionService sessionService) {
        this.categoryService = categoryService;
        this.sessionService = sessionService;
    }

    @PostMapping("/")
    public ResponseEntity<Category> addCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            Category category) {

        Session session = sessionService.getSession(cookie);

        Category category1 = categoryService.addCategory(category);
        return new ResponseEntity<>(category1, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id) {

        Session session = sessionService.getSession(cookie);

        Category category = categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> editCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id,
            @RequestBody EditCategoryRequestDto requestDto) {

        Session session = sessionService.getSession(cookie);

        //TODO:Editing part
        Category category = categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id) {

        Session session = sessionService.getSession(cookie);

        Category category = categoryService.getCategory(id);
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {

        Session session = sessionService.getSession(cookie);

        List<Category> allCategories = categoryService.getAllCategories();

        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }
}
