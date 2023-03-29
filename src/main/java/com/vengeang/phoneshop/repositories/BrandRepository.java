package com.vengeang.phoneshop.repositories;

import com.vengeang.phoneshop.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> , JpaSpecificationExecutor {
    List<Brand> findByNameContaining(String name);
    List<Brand> findByNameLike(String name);
}
