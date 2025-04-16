package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Booking;
import com.example.demo.model.BookingFilter;

import jakarta.persistence.criteria.Predicate;

public class BookingSpecification {
    public static Specification<Booking> withFilters(BookingFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.telefoneCliente() != null) {
                predicates.add(
                        cb.like(root.get("telefoneCliente"), "%" + filter.telefoneCliente() + "%"));
            }

            if (filter.mesa() != null) {
                predicates.add(
                        cb.equal(root.get("mesa"), filter.mesa()));
            }

            if (filter.dataReservaInicial() != null && filter.dataReservaFinal() != null) {
                predicates.add(
                        cb.between(root.get("dataReserva"), filter.dataReservaInicial(), filter.dataReservaFinal()));

            }

            if (filter.dataReservaInicial() != null && filter.dataReservaFinal() == null) {
                predicates.add( 
                        cb.equal(root.get("dataReserva"), filter.dataReservaInicial()));
               
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }

}