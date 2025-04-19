package com.example.demo.model;

import java.time.LocalDate;

public record BookingFilter (
    String telefoneCliente,
    LocalDate dataReservaInicial,
    LocalDate dataReservaFinal,
    String quantidadePessoas,
    Integer mesa){}
