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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    @Cacheable("usuarios")
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
    @CacheEvict(value = "usuarios", allEntries = true)
    @Operation(
        summary = "Cadastrar usuário",
        description = "Realizando o Cadastro de um usuário",
        responses = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "409") // Conflito (e-mail existente)
        }
    )
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        if (repository.findByEmailCliente(usuario.getEmailCliente()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body("E-mail já cadastrado.");
        }

        Usuario novoUsuario = repository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Listar os usuários fornecidos por ID",
        description = "Pega as informações criadas pelo usuário por ID",
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
        description = "Deleta o usuário com o ID fornecido",
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
        description = "Com o ID é possível alterar as informações",
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
