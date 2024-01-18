package com.salesianostriana.dam.UnitTestexample.repos;

import com.salesianostriana.dam.UnitTestexample.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepo extends JpaRepository<Producto, Long> {

    List<Producto> findByPrecioLessThan(double precio);

}
