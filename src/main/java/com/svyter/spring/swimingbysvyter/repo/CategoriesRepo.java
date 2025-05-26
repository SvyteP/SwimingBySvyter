package com.svyter.spring.swimingbysvyter.repo;

import com.svyter.spring.swimingbysvyter.entity.Categories;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepo extends CrudRepository<Categories, Integer> {
}
