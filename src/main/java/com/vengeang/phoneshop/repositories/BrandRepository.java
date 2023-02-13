package com.vengeang.phoneshop.repositories;

import com.vengeang.phoneshop.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    List<Brand> findByNameContaining(String name);
}
