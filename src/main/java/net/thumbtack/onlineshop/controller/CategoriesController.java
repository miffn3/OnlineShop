package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.CategoryCreateRequestDto;
import net.thumbtack.onlineshop.dto.request.CategoryUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.SessionDoesntExistException;
import net.thumbtack.onlineshop.service.iface.CategoryService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
            CategoryCreateRequestDto createRequestDto) {

        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }

        Category category = categoryService.addCategory(createRequestDto);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id) {

        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }

        Category category = categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> editCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id,
            @RequestBody CategoryUpdateRequestDto requestDto) {

        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }

        Category category = categoryService.updateCategory(requestDto, id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id) {

        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }

        Category category = categoryService.getCategory(id);
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Set<Category>> getAllCategories(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {

        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }

        Set<Category> allCategories = categoryService.getAllCategories();

        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }
}
