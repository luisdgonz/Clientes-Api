package com.cliente.clienteapi.springData.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private int id;
    private String nombre;
    private String apellidos;
    private String identificacion;
    private String paisResidencia;
    private String direccion;
    private String telefono;

    private Boolean estado;
}
