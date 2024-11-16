package com.example.facturacliente.models.dao;

import com.example.facturacliente.models.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IClientDao extends CrudRepository<Client, Long> {
}
