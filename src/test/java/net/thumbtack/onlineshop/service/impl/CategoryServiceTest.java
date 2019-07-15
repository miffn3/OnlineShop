package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.repository.iface.CategoryRepository;
import net.thumbtack.onlineshop.service.iface.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class CategoryServiceTest {
    private CategoryService underTest;

    @Mock
    private CategoryRepository categoryRepository;

    @Captor
    public ArgumentCaptor<Category> captor;

    @Captor
    public ArgumentCaptor<Integer> captorInt;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new CategoryServiceImpl(categoryRepository);
    }


    @Test
    public void addCategory() {
       Category category = new Category(1,"tv",0,"source");

        underTest.addCategory(category);
        verify(categoryRepository).addCategory(captor.capture());
        Category value = captor.getValue();
        assertEquals(category,value);
    }

    @Test
    public void getCategory() {
        Category category = new Category(1,"tv",0,"source");

        underTest.getCategory(category.getId());
        verify(categoryRepository).getCategoryById(captorInt.capture());
        Integer value = captorInt.getValue();
        assertEquals((Integer)category.getId(),value);
    }

    @Test
    public void updateCategory() {
        Category category = new Category(1,"tv",0,"source");

        underTest.updateCategory(category);
        verify(categoryRepository).updateCategory(captor.capture());
        Category value = captor.getValue();
        assertEquals(category,value);
    }
}