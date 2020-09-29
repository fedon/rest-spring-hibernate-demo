package org.fedon.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.fedon.demo.model.Category;
import org.fedon.demo.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Dmytro Fedonin
 *
 */
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryService service;

    @Test
    public void createCategoryTop() {
        service.createCategory("name", null);

        verify(repository).save(any());
    }

    @Test
    public void createCategoryChild() {
        String parentName = "parent";
        Category parent = new Category(parentName);
        when(repository.findByName(parentName)).thenReturn(Optional.of(parent));
        service.createCategory("name", parentName);

        verify(repository).findByName(parentName);
        verify(repository).save(any());
    }
}
