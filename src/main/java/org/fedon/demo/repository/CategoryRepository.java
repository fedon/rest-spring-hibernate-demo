package org.fedon.demo.repository;

import org.fedon.demo.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmytro Fedonin
 *
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
