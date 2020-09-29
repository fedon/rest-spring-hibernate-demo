package org.fedon.demo.repository;

import org.fedon.demo.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmytro Fedonin
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
