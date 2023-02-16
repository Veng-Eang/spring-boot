package com.vengeang.phoneshop.spec;

import com.vengeang.phoneshop.entities.Brand;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
