package com.salesianostriana.dam.UnitTestexample.service;

import com.salesianostriana.dam.UnitTestexample.dto.GetProductoDto;
import com.salesianostriana.dam.UnitTestexample.model.Producto;
import com.salesianostriana.dam.UnitTestexample.repos.ProductoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepo productoRepo;

    public Producto save(Producto producto){
        return productoRepo.save(producto);
    }

    public List<Producto> saveAll (List<Producto> p){
        return productoRepo.saveAll(p);
    }

    public List<Producto> findAll (){
        return productoRepo.findAll();
    }

    public List<Producto> precioMenorQue10(){
        return productoRepo.findByPrecioLessThan(10);
    }

    public List<GetProductoDto> precioMenorQue10YDisponible(){
        List<Producto> todos= productoRepo.findAll();

        List<GetProductoDto> resultado = new ArrayList<>();

        for (Producto p : todos){
            if (p.getPrecio() < 10)
                if (p.isDisponible())
                    resultado.add(GetProductoDto.of(p));
        }

        if (resultado.isEmpty())
            return null;
        return resultado;
    }
}
