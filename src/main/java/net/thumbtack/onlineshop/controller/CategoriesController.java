package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.CategoryCreateRequestDto;
import net.thumbtack.onlineshop.dto.request.EditCategoryRequestDto;
import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.service.iface.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoriesController {
    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/")
    public ResponseEntity<Category> addCategory(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
                    CategoryCreateRequestDto categoryCreateRequestDto) {
        Category categoryParent = this.categoryService.getCategory(categoryCreateRequestDto.getParentId());
        Category category = new Category(0, categoryCreateRequestDto.getName(),
                categoryCreateRequestDto.getParentId(), categoryParent.getName());
        this.categoryService.addCategory(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
                                                @PathVariable int id) {
        Category category = this.categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> editCategory(@CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
                                                 @PathVariable int id, @RequestBody EditCategoryRequestDto editCategoryRequestDto) {
        //TODO:Editing part
        Category category = this.categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
                               @PathVariable int id) {
        Category category = this.categoryService.getCategory(id);
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {
        List<Category> allCategories = this.categoryService.getAllCategories();

        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }
}
