package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.CategoryCreateRequestDto;
import net.thumbtack.onlineshop.dto.request.CategoryUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.SessionAccessDeniedException;
import net.thumbtack.onlineshop.exception.SessionDoesntExistException;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.CategoryService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/categories")
public class CategoriesController {
    private final CategoryService categoryService;
    private final SessionService sessionService;
    private final AdministratorService administratorService;

    public CategoriesController(CategoryService categoryService, SessionService sessionService,
                                AdministratorService administratorService) {
        this.categoryService = categoryService;
        this.sessionService = sessionService;
        this.administratorService = administratorService;
    }

    @PostMapping("/")
    public ResponseEntity<Category> addCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            CategoryCreateRequestDto createRequestDto) {
        checkSession(cookie);
        Category category = categoryService.addCategory(createRequestDto);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id) {
        checkSession(cookie);
        Category category = categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> editCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id,
            @RequestBody CategoryUpdateRequestDto requestDto) {
        checkSession(cookie);
        Category category = categoryService.updateCategory(requestDto, id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @PathVariable Long id) {
        checkSession(cookie);
        Category category = categoryService.getCategory(id);
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Set<Category>> getAllCategories(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {
        checkSession(cookie);
        Set<Category> allCategories = categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    private void checkSession(@CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {
        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }
        Administrator administrator = administratorService.getAdministratorById(session.getUserId());
        if (administrator == null) {
            throw new SessionAccessDeniedException();
        }
    }
}
