package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class Booking {
    
    private Long id;
    private String user;
    private String telefoneCliente;
    private String emailCliente;
    private LocalDate dataReserva;
    private LocalTime horaReserva;
    private int quantidadePessoas;
    private Boolean status;
    private int mesa;

    public Booking(Long id, String user, String telefoneCliente, String emailCliente, LocalDate dataReserva,
            LocalTime horaReserva, int quantidadePessoas, Boolean status, int mesa) {
        this.id = (id == null) ? new Random().nextLong() : id;
        this.user = user;
        this.telefoneCliente = telefoneCliente;
        this.emailCliente = emailCliente;
        this.dataReserva = dataReserva;
        this.horaReserva = horaReserva;
        this.quantidadePessoas = quantidadePessoas;
        this.status = status;
        this.mesa = mesa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getMesa() {
        return mesa;
    }   

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    
}
