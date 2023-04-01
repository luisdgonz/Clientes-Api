package com.cliente.clienteapi.springData.services;

import com.cliente.clienteapi.springData.dto.ClienteDto;
import com.cliente.clienteapi.springData.model.Cliente;
import com.cliente.clienteapi.springData.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ClienteService {
    private ClienteRepository clienteRepository;

    private ClienteDto fromClienteToDto(Cliente cliente){
        ClienteDto clienteDto = new ClienteDto();
        BeanUtils.copyProperties(cliente, clienteDto);
        return clienteDto;
    }

    public ClienteDto obtenerCliente(int id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> {throw new RuntimeException("Cliente no existe");});
        return fromClienteToDto(cliente);
    }

    public List<ClienteDto> listarTodosLosClientes(){
        List<ClienteDto> clienteDtoList = new ArrayList<>();
        clienteRepository
                .findAll()
                .stream()
                .map(cliente -> {
                    clienteDtoList.add(fromClienteToDto(cliente));
                    return cliente;
                })
                .collect(Collectors.toList());
        return clienteDtoList;
    }

    public List<ClienteDto> buscarClientesPorIdentifcacion(String identificacion){
        List<ClienteDto> resultadoClientesDto = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findClientesByIdentificacionAndAndEstadoIsTrue(identificacion);
        clientes.forEach(cliente -> {
            ClienteDto clienteDto = obtenerCliente(cliente.getId());
            resultadoClientesDto.add(clienteDto);
            System.out.println(clienteDto);
        });
        return resultadoClientesDto;
    }

    public void insertarCliente(ClienteDto clienteDto){
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setIdentificacion(clienteDto.getIdentificacion());
        cliente.setPaisResidencia(clienteDto.getPaisResidencia());
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setEstado(clienteDto.getEstado());
        clienteRepository.save(cliente);
    }

    public void actualizarCliente(ClienteDto clienteDto){
        Cliente cliente = new Cliente();
        ClienteDto clienteDtoAct = new ClienteDto();
        clienteDtoAct = obtenerCliente(clienteDto.getId());
        cliente.setId(clienteDto.getId());
        cliente.setNombre(clienteDtoAct.getNombre());
        cliente.setApellidos(clienteDtoAct.getApellidos());
        cliente.setIdentificacion(clienteDtoAct.getIdentificacion());
        cliente.setPaisResidencia(clienteDto.getPaisResidencia());
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setEstado(clienteDtoAct.getEstado());
        clienteRepository.save(cliente);
    }

    public ClienteDto desactivarCuentaPorId(ClienteDto cuentaDto){
        Cliente cliente = clienteRepository.findById(cuentaDto.getId()).orElseThrow(() -> {throw new RuntimeException("Cliente No Existe");});
        cliente.setEstado(false);
        clienteRepository.save(cliente);
        return fromClienteToDto(cliente);
    }
}
