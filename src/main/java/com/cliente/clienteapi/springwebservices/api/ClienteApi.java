package com.cliente.clienteapi.springwebservices.api;

import com.cliente.clienteapi.springData.dto.ClienteDto;
import com.cliente.clienteapi.springData.services.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/v1/api/cliente")
@Slf4j
public class ClienteApi {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/all")
    public List<ClienteDto> listarTodosLosClientes(){
        return clienteService.listarTodosLosClientes();
    }

    @GetMapping("/id/{id}")
    public ClienteDto buscarCliente(@PathVariable int id){
        log.info("Busquedad del cliente: {}", id);
        if (id == 1000) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        } else if (id == 1001) {
            throw new RuntimeException("Cliente tiene la información incompleta.");
        }  else if (id == 1002) {
            String s = null;
            s.toLowerCase();
        }
        return clienteService.obtenerCliente(id);
    }

    @GetMapping(value = "/{identificacion}")
    public List<ClienteDto> buscarClientesPorIdentifcacion(@PathVariable String identificacion){
        log.info("Obtener clientes por identificacion: {}", identificacion);
        return clienteService.buscarClientesPorIdentifcacion(identificacion);
    }

    @PostMapping
    public void guardarCliente(@RequestBody ClienteDto clienteDTO){
        log.info("Guardar el cliente: {}", clienteDTO);
        clienteService.insertarCliente(clienteDTO);
    }

    @PutMapping
    public void actualizarCliente(@RequestBody ClienteDto clienteDTO){
        log.info("Guardar el cliente: {}", clienteDTO);
        clienteService.actualizarCliente(clienteDTO);
    }

    @PostMapping(value = "/desactivar")
    public ClienteDto desactivarPorId(@RequestBody ClienteDto clienteDTO){
        log.info("Desactivar Cuenta por:", clienteDTO);
        return clienteService.desactivarCuentaPorId(clienteDTO);
    }
}
