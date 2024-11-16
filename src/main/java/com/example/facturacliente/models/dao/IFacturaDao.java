package com.example.facturacliente.models.dao;

import com.example.facturacliente.models.entity.Factura;
import org.springframework.data.repository.CrudRepository;

public interface IFacturaDao extends CrudRepository<Factura, Long> {
}
