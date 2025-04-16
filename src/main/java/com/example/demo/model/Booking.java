package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;
// import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@Entity
@AllArgsConstructor

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telefoneCliente;
    private LocalDate dataReserva;
    private LocalTime horaReserva;
    private int quantidadePessoas;
    private int mesa;

    public Booking(String user, String telefoneCliente, String emailCliente, LocalDate dataReserva,
            LocalTime horaReserva, int quantidadePessoas, Boolean status, int mesa) {
        this.telefoneCliente = telefoneCliente;
        this.dataReserva = dataReserva;
        this.horaReserva = horaReserva;
        this.quantidadePessoas = quantidadePessoas;
        this.mesa = mesa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalTime getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(LocalTime horaReserva) {
        this.horaReserva = horaReserva;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public int getMesa() {
        return mesa;
    }   

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    
}
