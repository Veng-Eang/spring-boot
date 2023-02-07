package com.vengeang.phoneshop.repositories;

import com.vengeang.phoneshop.entities.Brand;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
}
