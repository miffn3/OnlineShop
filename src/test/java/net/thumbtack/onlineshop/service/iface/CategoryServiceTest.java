package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.dto.request.CategoryCreateRequestDto;
import net.thumbtack.onlineshop.dto.request.CategoryUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Category;
import net.thumbtack.onlineshop.repository.iface.CategoryRepository;
import net.thumbtack.onlineshop.service.iface.CategoryService;
import net.thumbtack.onlineshop.service.impl.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {
    private CategoryService underTest;

    @Mock
    private CategoryRepository categoryRepository;

    @Captor
    public ArgumentCaptor<Category> captor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void addCategory() {
        Category category = new Category();
        category.setName("tv");

        CategoryCreateRequestDto createRequestDto = new CategoryCreateRequestDto();
        createRequestDto.setName("tv");

        underTest.addCategory(createRequestDto);

        verify(categoryRepository).save(captor.capture());
        Category value = captor.getValue();
        assertEquals(category,value);
    }

    @Test
    public void getCategory() {
        Category category = new Category();
        category.setName("tv");
        category.setId(1L);

        Optional<Category> opt = Optional.of(category);

        when(categoryRepository.findById(1L)).thenReturn(opt);

        Category categoryTmp = underTest.getCategory(category.getId());
        assertEquals(category, categoryTmp);
    }

    @Test
    public void updateCategory() {
        Category category = new Category();
        category.setName("tv");
        category.setId(1L);

        CategoryUpdateRequestDto updateRequestDto = new CategoryUpdateRequestDto();
        updateRequestDto.setName("not_tv");

        Category updatedCategory = new Category();
        updatedCategory.setId(category.getId());
        updatedCategory.setName(updateRequestDto.getName());

        Optional<Category> opt = Optional.of(category);
        when(categoryRepository.findById(1L)).thenReturn(opt);

        underTest.updateCategory(updateRequestDto, category.getId());

        verify(categoryRepository).save(captor.capture());
        Category value = captor.getValue();
        assertEquals(updatedCategory, value);
    }

    @Test
    public void getAllCategories() {
        Category category = new Category();
        category.setName("tv");
        category.setId(1L);

        Set<Category> categories = new HashSet<>();
        categories.add(category);

        when(categoryRepository.findAll()).thenReturn(categories);

        Set<Category> categoriesTmp= underTest.getAllCategories();
        assertEquals(categories, categoriesTmp);
    }

}