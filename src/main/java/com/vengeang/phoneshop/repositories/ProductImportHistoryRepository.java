package com.vengeang.phoneshop.repositories;

import com.vengeang.phoneshop.entities.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory,Long> {
}
