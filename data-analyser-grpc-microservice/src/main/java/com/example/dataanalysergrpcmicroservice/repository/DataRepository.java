package com.example.dataanalysergrpcmicroservice.repository;

import com.example.dataanalysergrpcmicroservice.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
    @Query(value = """
                SELECT *
                FROM data
                OFFSET (SELECT current_offset FROM offsets LIMIT 1)
                LIMIT :batchSize
            """, nativeQuery = true)
    List<Data> findAllWithOffset(@Param("batchSize") long batchSize);

    @Modifying
    @Query(value = """
            UPDATE offsets SET current_offset = current_offset + :batchSize
            """, nativeQuery = true)
    void incrementOffset(@Param("batchSize") long batchSize);

}
