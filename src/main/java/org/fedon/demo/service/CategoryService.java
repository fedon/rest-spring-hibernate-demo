package org.fedon.demo.service;

import java.util.Set;

import org.fedon.demo.model.Category;
import org.fedon.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dmytro Fedonin
 *
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category createCategory(String name, String parentName) {
        Category category = null;
        if (parentName == null) {
            category = new Category(name);
        } else {
            Category parent = repository.findByName(parentName).get();
            category = new Category(name, parent);
        }
        return repository.save(category);
    }

    /**
     * @param id
     *            of Category
     * @return children of specified Category
     */
    public Set<Category> listChildren(Long id) {
        return repository.findById(id).get().getChildren();
    }

    /**
     * @param id
     *            of Category
     * @return parent Category
     */
    public Category parent(Long id) {
        return repository.findById(id).get().getParent();
    }

    /**
     * @param id
     * @return
     */
    public Category read(Long id) {
        return repository.findById(id).get();
    }

    /**
     * @param id
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
