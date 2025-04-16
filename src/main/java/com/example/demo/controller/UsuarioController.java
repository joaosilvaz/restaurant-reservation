package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    @Cacheable ("usuarios")
    @Operation(
            summary = "Listar todos os usuários",
            description = "Lista todos os usuários cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200")

            }
    )
    
    public List<Usuario> index() {
        return repository.findAll();
    }

    @PostMapping
    @CacheEvict (value = "usuarios", allEntries = true)
    @Operation(
            summary = "Cadastrar usuário",
            description = "Realizando o Cadastro de um usuário",
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "400")
            }
    )
     @ResponseStatus(
        code = HttpStatus.CREATED
    )

    public Usuario create(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Listar os usuários fornecidos por ID",
            description = "Pegando as informações criada pelo usuário, ou seja ele pega por ID",
            responses = {
                    @ApiResponse(responseCode = "200")

            }
    )
    public Usuario show(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar os usuários fornecidos por ID",
            description = "Inserindo o ID do usuário é possível deleta-lo, vale ressaltar que deleta apenas o ID fornecido",
            responses = {
                    @ApiResponse(responseCode = "200")

            }
    )
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Alterar os usuários fornecidos por ID",
            description = "Com o ID é possível alterar as infromações, utilizado bastante com após um erro de digitação ou uma alterção de senha ou e-mail",
            responses = {
                    @ApiResponse(responseCode = "200")

            }
    )
    public Usuario update(@PathVariable Long id, @RequestBody Usuario updatedUsuario) {
        return repository.findById(id)
                .map(u -> {
                    updatedUsuario.setId(id);
                    return repository.save(updatedUsuario);
                })
                .orElseThrow();
    }
}