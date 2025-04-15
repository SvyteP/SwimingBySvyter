package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.Complexity;
import com.svyter.spring.swimingbysvyter.entity.Inventory;
import com.svyter.spring.swimingbysvyter.entity.Trainings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingsRepo extends CrudRepository<Trainings, Long> {
    List<Trainings> findAll();
    @Query(value = """
            SELECT t.* FROM trainings t
            join trainings_has_inventory thi on t.id = thi.trainings_id
            WHERE thi.inventory_id in (:inventoriesId) and t.complexity_id = :complexityId
            group by t.id
            HAVING count(thi.inventory_id) = :countInventoriesId ;""",
            nativeQuery = true)
    List<Trainings> findByInventoriesIdAndComplexityIdAndCountInventoriesId(@Param("inventoriesId") List<Long> inventoriesId, @Param("complexityId") Long complexityId, @Param("countInventoriesId") int countInventoriesId);
}
