package com.vengeang.phoneshop.repositories;

import com.vengeang.phoneshop.entities.Brand;
import com.vengeang.phoneshop.entities.Model;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model,Long>, JpaSpecificationExecutor {
    List<Model> findModelByBrand_Id(Long brandId);
}
