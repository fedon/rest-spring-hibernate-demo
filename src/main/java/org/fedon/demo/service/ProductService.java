package org.fedon.demo.service;

import java.math.BigDecimal;
import java.util.Set;

import org.fedon.demo.model.Category;
import org.fedon.demo.model.Product;
import org.fedon.demo.repository.CategoryRepository;
import org.fedon.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dmytro Fedonin
 *
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * @param name
     * @param price
     * @return newly created Product
     */
    public Product createProduct(String name, BigDecimal price) {
        return createProduct(name, price, null);
    }

    /**
     * @param name
     * @param categoryName
     * @return binded Category
     */
    public Category bindCategory(Long id, String categoryName) {
        Category category = categoryRepository.findByName(categoryName).get();
        Product product = productRepository.findById(id).get();
        category.getProducts().add(product);
        return categoryRepository.save(category);
    }

    /**
     * @param name
     * @param categoryName
     * @return updated product
     */
    public void unbindCategory(Long id, String categoryName) {
        Category category = categoryRepository.findByName(categoryName).get();
        Product product = productRepository.findById(id).get();
        category.getProducts().remove(product);
        categoryRepository.save(category);
    }

    public Set<Product> listProducts(String categoryName) {
        return categoryRepository.findByName(categoryName).get().getProducts();
    }

    /**
     * @param name
     * @param price
     * @param description
     * @return newly created Product
     */
    public Product createProduct(String name, BigDecimal price, String description) {
        Product product = new Product(name, price, description);
        return productRepository.save(product);
    }
}
