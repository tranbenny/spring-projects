package com.bennytran.currencyapi.repository;

import com.bennytran.currencyapi.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Extending JpaRepository will implement all CRUD operations
 */
@Repository
public interface RateRepository extends JpaRepository<Rate, String> {
    List<Rate> findByDate(Date date); // look for rates with this date
    Rate findByDateAndCode(Date date, String code); // look for rates with this date and code
}
