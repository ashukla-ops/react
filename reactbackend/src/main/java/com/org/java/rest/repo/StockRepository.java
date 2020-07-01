package com.org.java.rest.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.java.rest.model.StockDTO;

@Repository
public interface StockRepository extends JpaRepository<StockDTO,Long>{

}
