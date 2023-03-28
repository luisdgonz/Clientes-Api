package com.cliente.clienteapi.springData.repository;

import com.cliente.clienteapi.springData.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    List<Cliente> findClientesByIdentificacionAndAndEstadoIsTrue(String identificacion);
}
