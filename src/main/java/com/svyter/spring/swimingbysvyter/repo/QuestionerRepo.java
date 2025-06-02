package com.svyter.spring.swimingbysvyter.repo;

import com.svyter.spring.swimingbysvyter.entity.Questioner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionerRepo extends CrudRepository<Questioner,Long> {
    Optional<Questioner> findByCustomersId(Long id);
}
