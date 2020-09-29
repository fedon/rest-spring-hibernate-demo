package org.fedon.demo.api;

import java.math.BigDecimal;
import java.util.Set;

import org.fedon.demo.model.Category;
import org.fedon.demo.model.Product;
import org.fedon.demo.service.ProductService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dmytro Fedonin
 *
 */
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @RequestMapping(value = "/{name}/{price}", method = RequestMethod.POST)
    public Product createProduct(@PathVariable("name") @NonNull String name, @PathVariable("price") @NonNull BigDecimal price,
            @RequestBody String description) {
        return service.createProduct(name, price, description);
    }

    @RequestMapping(value = "/{id}/{categoryName}", method = RequestMethod.PUT)
    public Category bindCategory(@PathVariable("id") Long id,
            @PathVariable("categoryName") @NonNull @Length(min = 1) String categoryName) {
        return service.bindCategory(id, categoryName);
    }

    @RequestMapping(value = "/{id}/{categoryName}", method = RequestMethod.DELETE)
    public void unbindCategory(@PathVariable("id") Long id, @PathVariable("categoryName") @NonNull @Length(min = 1) String categoryName) {
        service.unbindCategory(id, categoryName);
    }

    @RequestMapping(value = "/{categoryName}", method = RequestMethod.GET)
    public Set<Product> listProducts(@PathVariable("categoryName") @NonNull @Length(min = 1) String categoryName) {
        return service.listProducts(categoryName);
    }
}
