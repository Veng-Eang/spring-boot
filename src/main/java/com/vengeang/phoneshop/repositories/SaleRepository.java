package com.vengeang.phoneshop.repositories;

import com.vengeang.phoneshop.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
