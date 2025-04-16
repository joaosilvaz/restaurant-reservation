package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.BookingFilter;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.BookingSpecification;
import org.springframework.data.domain.Pageable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository repository;

    @GetMapping
    
    @Operation(
            summary = "Listar todas as reservas",
            description = "Listando as reservas criadas pelo usuário",
            responses = {
                    @ApiResponse(responseCode = "200")
            }
    )
    public List<Booking> index() {
        return repository.findAll();
    }
    public Page<Booking> index( 
        BookingFilter filter,
        @PageableDefault (size = 10, sort = "date", direction = Direction.DESC)Pageable pageable){



                // var probe = Booking.builder()
                // .telefoneCliente(filter.telefoneCliene())
                // .mesa(filter.mesa())
                // .dataReserva(filter.startDate)
                // .build();

                //  var matcher = ExampleMatcher
                //         .matchingAll()
                //         .withIgnoreCase()
                //         .

                // var example =Example.of(probe);
                var specification = BookingSpecification.withFilters(filter);

                return repository.findAll(specification, pageable);
    }



    @PostMapping
    @Operation(
            summary = "Cadastrar Reserva",
            description = "Insere uma reserva",
            responses = {
                    @ApiResponse(responseCode = "200")
  
            }
    )
    @ResponseStatus(
        code = HttpStatus.CREATED
    )
    public Booking create(@RequestBody Booking booking) {
        return repository.save(booking);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Listando as reservas por usuário",
            description = "Demonstra as reservas que apenas o usuário realizou, ou seja, pelo (id)",
            responses = {
                    @ApiResponse(responseCode = "200")

            }
    )
    public Booking show(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletando a reserva do usuário",
            description = "Deletando a reserva apenas que o usuário criou, ou seja ele deleta por ID",
            responses = {
                    @ApiResponse(responseCode = "200")

            }
    )

    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Alterando a reserva por usuário(ID)",
            description = "Alterando a reserva apenas que o usuário criou, ou seja ela é alterada por ID",
            responses = {
                    @ApiResponse(responseCode = "200")

            }
    )
    public Booking update(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        return repository.findById(id)
                .map(b -> {
                    updatedBooking.setId(id);
                    return repository.save(updatedBooking);
                })
                .orElseThrow();
    }
}