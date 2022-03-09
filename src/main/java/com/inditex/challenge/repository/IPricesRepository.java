package com.inditex.challenge.repository;

import com.inditex.challenge.repository.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPricesRepository extends JpaRepository<Price, Long> {
    @Query(
            value = """
    SELECT * FROM prices 
    WHERE product_id = :productId 
    AND :applyDate BETWEEN start_date and end_date
    """, nativeQuery = true
    )
    List<Price> findRecordsByProductIdAndApplyDate(Integer productId, String applyDate);
}