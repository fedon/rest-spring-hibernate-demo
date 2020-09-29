package org.fedon.demo.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;

import org.fedon.demo.model.Category;
import org.fedon.demo.service.CategoryService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dmytro Fedonin
 *
 */

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    /**
     * Create top category.
     * 
     * @param name
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public Category createTopCategory(@PathVariable("name") @NonNull String name) {
        return service.createCategory(name, null);
    }

    /**
     * Create child category.
     * 
     * @param name
     * @param parentName
     */
    @RequestMapping(value = "/{name}/{parentName}", method = RequestMethod.POST)
    public Category createCategory(@PathVariable("name") @NonNull String name,
            @PathVariable("parentName") @NonNull @Length(min = 1) String parentName) {
        return service.createCategory(name, parentName);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Category read(@PathVariable("id") @Min(0L) Long id) {
        return service.read(id);
    }

    @RequestMapping(value = "/children/{id}", method = RequestMethod.GET)
    public List<String> listCategory(@PathVariable("id") @Min(0L) Long id) {
        return service.listChildren(id).stream().map(c -> c.getName()).collect(Collectors.toList());
    }
}
