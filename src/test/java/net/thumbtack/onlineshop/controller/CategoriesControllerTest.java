package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.CategoryCreateRequestDto;
import net.thumbtack.onlineshop.dto.request.CategoryUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.CategoryService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CategoriesControllerTest {
    private CategoriesController underTest;

    @Mock
    private CategoryService categoryService;

    @Mock
    private SessionService sessionService;

    @Mock
    private AdministratorService administratorService;

    @Before
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
        underTest = new CategoriesController(this.categoryService, this.sessionService, this.administratorService);
    }

    @Test
    public void addCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("tv");

        CategoryCreateRequestDto createRequestDto = new CategoryCreateRequestDto();
        createRequestDto.setName("tv");

        Session session = new Session();
        session.setCookie("cookie");
        session.setUserId(1L);

        when(sessionService.getSession(session.getCookie())).thenReturn(session);
        when(categoryService.addCategory(createRequestDto)).thenReturn(category);

        Category response = underTest.addCategory(session.getCookie(), createRequestDto).getBody();
        assertEquals(category, response);
    }

    @Test
    public void getCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("tv");

        Session session = new Session();
        session.setCookie("cookie");
        session.setUserId(1L);

        when(sessionService.getSession(session.getCookie())).thenReturn(session);
        when(categoryService.getCategory(1L)).thenReturn(category);

        Category response = underTest.getCategory(session.getCookie(), category.getId()).getBody();
        assertEquals(category, response);
    }

    @Test
    public void editCategory() {
        Category category = new Category();
        category.setName("tv");
        category.setId(1L);

        CategoryUpdateRequestDto updateRequestDto = new CategoryUpdateRequestDto();
        updateRequestDto.setName("not_tv");

        Category updatedCategory = new Category();
        updatedCategory.setId(category.getId());
        updatedCategory.setName(updateRequestDto.getName());

        Session session = new Session();
        session.setCookie("cookie");
        session.setUserId(1L);

        when(sessionService.getSession(session.getCookie())).thenReturn(session);
        when(categoryService.updateCategory(updateRequestDto, category.getId())).thenReturn(updatedCategory);

        Category response = underTest.editCategory(session.getCookie(), category.getId(), updateRequestDto).getBody();
        assertEquals(updatedCategory, response);
    }

    @Test
    public void getAllCategories() {
        Category category = new Category();
        category.setName("tv");
        category.setId(1L);

        Set<Category> categories = new HashSet<>();
        categories.add(category);

        Session session = new Session();
        session.setCookie("cookie");
        session.setUserId(1L);

        when(sessionService.getSession(session.getCookie())).thenReturn(session);
        when(categoryService.getAllCategories()).thenReturn(categories);

        Set<Category> response = underTest.getAllCategories(session.getCookie()).getBody();
        assertEquals(categories, response);
    }
}