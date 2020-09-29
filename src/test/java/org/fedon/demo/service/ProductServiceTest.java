package org.fedon.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;

import org.fedon.demo.model.Category;
import org.fedon.demo.model.Product;
import org.fedon.demo.repository.CategoryRepository;
import org.fedon.demo.repository.ProductRepository;
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
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService service;

    @Test
    public void bindCategory() {
        String name = "name";
        Category parent = new Category(name);
        parent.setProducts(new HashSet<>());
        when(categoryRepository.findByName(name)).thenReturn(Optional.of(parent));
        when(productRepository.findById(1L)).thenReturn(Optional.of(new Product()));
        service.bindCategory(1l, name);

        verify(categoryRepository).findByName(name);
        verify(productRepository).findById(1L);
        verify(categoryRepository).save(any());
    }
}
