package com.vengeang.phoneshop.spec;

import com.vengeang.phoneshop.entities.Brand;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class BrandSpec implements Specification<Brand> {
    private final BrandFilter brandFilter;
    List<Predicate> predicates = new ArrayList<>();
    @Override
    public Predicate toPredicate(Root<Brand> brandRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if(brandFilter.getName()!=null){
            predicates.add(cb.like(cb.upper(brandRoot.get("name")),"%"+brandFilter.getName().toUpperCase()+"%"));
        }
        if(brandFilter.getId()!=null){
            predicates.add(brandRoot.in(brandFilter.getId()));
        }
        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
