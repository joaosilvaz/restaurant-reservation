package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Booking;
import com.example.demo.model.BookingFilter;

import jakarta.persistence.criteria.Predicate;

public class BookingSpecification {
    public static Specification<Booking> withFilters(BookingFilter filter){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.telefoneCliene() != null){
                predicates.add(
                    cb.like(root.get("booking"), filter.telefoneCliene())
                );
            }
            if (filter.mesa() != null){
                predicates.add(
                    cb.like(root.get("booking"), String.valueOf(filter.mesa()))
                );

            }

            if (filter.dataReservaInicial() != null && filter.dataReservaFinal() != null){
                predicates.add(
                    cb.between(root.get("date"), filter.(()));
                )

            }
            if (filter.dataReserva() != null && filter.endDate() == null){
                
            }
        };
    }
    
}
