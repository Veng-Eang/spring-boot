package com.vengeang.phoneshop.repositories;

import com.vengeang.phoneshop.entities.Model;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model,Integer>, JpaSpecificationExecutor {
}
