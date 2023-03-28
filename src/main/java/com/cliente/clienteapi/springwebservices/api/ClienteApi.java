package com.cliente.clienteapi.springwebservices.api;

import com.cliente.clienteapi.springData.dto.ClienteDto;
import com.cliente.clienteapi.springData.services.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
