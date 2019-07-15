package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.CategoryCreateRequestDto;
import net.thumbtack.onlineshop.dto.request.EditCategoryRequestDto;
import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.ServerException;
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
    public ResponseEntity addCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            CategoryCreateRequestDto categoryCreateRequestDto) {

        Session session;
        try {
            session = sessionService.getSession(cookie);
        } catch (ServerException ex) {
            System.out.println(ex.getServerErrorCode());
            return null;
        }

        Category categoryParent = categoryService.getCategory(categoryCreateRequestDto.getParentId());

        Category category = new Category(0, categoryCreateRequestDto.getName(),
                categoryCreateRequestDto.getParentId(), categoryParent.getName());

        categoryService.addCategory(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable int id) {

        Session session;
        try {
            session = sessionService.getSession(cookie);
        } catch (ServerException ex) {
            System.out.println(ex.getServerErrorCode());
            return null;
        }

        Category category = categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable int id,
            @RequestBody EditCategoryRequestDto requestDto) {

        Session session;
        try {
            session = sessionService.getSession(cookie);
        } catch (ServerException ex) {
            System.out.println(ex.getServerErrorCode());
            return null;
        }

        //TODO:Editing part
        Category category = categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable int id) {

        Session session;
        try {
            session = sessionService.getSession(cookie);
        } catch (ServerException ex) {
            System.out.println(ex.getServerErrorCode());
            return null;
        }

        Category category = categoryService.getCategory(id);
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity getAllCategories(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {

        Session session;
        try {
            session = sessionService.getSession(cookie);
        } catch (ServerException ex) {
            System.out.println(ex.getServerErrorCode());
            return null;
        }

        List<Category> allCategories = categoryService.getAllCategories();

        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }
}
