package com.salesianostriana.dam.UnitTestexample.dto;

import com.salesianostriana.dam.UnitTestexample.model.Producto;
import lombok.Builder;

@Builder
public class GetProductoDto {

    Long id;
    String nombre;

    static public GetProductoDto of (Producto p){
        return GetProductoDto.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .build();
    }
}
