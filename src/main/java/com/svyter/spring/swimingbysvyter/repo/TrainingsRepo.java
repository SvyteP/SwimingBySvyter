package com.svyter.spring.swimingbysvyter.repo;

import com.svyter.spring.swimingbysvyter.entity.Trainings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingsRepo extends CrudRepository<Trainings, Long> {
    List<Trainings> findAll();
    boolean existsByName(String name);
    @Query(value = """
            SELECT t.*
            FROM trainings t
            WHERE t.complexity_id = :complexityId
              AND t.id NOT IN (
                SELECT thi.trainings_id
                FROM trainings_has_inventory thi
                WHERE thi.inventory_id NOT IN (:inventoriesId)
              );""",
            nativeQuery = true)
    List<Trainings> findByInventoriesIdAndComplexityIdAndCountInventoriesId(@Param("inventoriesId") List<Long> inventoriesId, @Param("complexityId") Long complexityId);

    @Query(value = """
            SELECT t.* FROM trainings t
            left join trainings_has_inventory thi on t.id = thi.trainings_id
            WHERE t.complexity_id = :complexityId AND thi.inventory_id is null
            group by t.id ;""",
            nativeQuery = true)
    List<Trainings> findByComplexityIdAndNullInventoriesId(@Param("complexityId") Long complexityId);

}
