package com.example.demo.config;

import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Configuration
public class DatabaseSeeder {

    @Autowired
    private BookingRepository bookingRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public DatabaseSeeder(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }



    @PostConstruct
    public void init() {

        var bookings = List.of(
                Booking.builder().dataReserva(LocalDate.of(2025, 12, 12)).horaReserva(LocalTime.of(13, 00)).quantidadePessoas(4).mesa(5).telefoneCliente("11999999999").build(),
                Booking.builder().dataReserva(LocalDate.of(2025, 1, 15)).horaReserva(LocalTime.of(15, 40)).quantidadePessoas(2).mesa(5).telefoneCliente("11888888888").build(),
                Booking.builder().dataReserva(LocalDate.of(2025, 12, 25)).horaReserva(LocalTime.of(18, 20)).quantidadePessoas(3).mesa(5).telefoneCliente("11777777777").build(),
                Booking.builder().dataReserva(LocalDate.of(2025, 4, 3)).horaReserva(LocalTime.of(20, 30)).quantidadePessoas(1).mesa(5).telefoneCliente("11666666666").build()
        );

        bookingRepository.saveAll(bookings);

        var usuario = List.of(
                Usuario.builder().nome("João Pedro").emailCliente("jp@gmail.com").validacao("123456").build(),
                Usuario.builder().nome("João").emailCliente("jota@gmail.com").validacao("123").build(),
                Usuario.builder().nome("Pedro").emailCliente("pedro@gmail.com").validacao("1234").build()
        );

        usuarioRepository.saveAll(usuario);


        }
        
    }


