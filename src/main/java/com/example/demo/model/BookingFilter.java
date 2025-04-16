package com.example.demo.model;

import java.time.LocalDate;

public record BookingFilter (
    String telefoneCliene,
    LocalDate dataReservaInicial,
    LocalDate dataReservaFinal,
    Integer mesa){}
