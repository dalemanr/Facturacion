package com.example.facturacliente.service;

import com.example.facturacliente.models.entity.Client;
import com.example.facturacliente.models.entity.Factura;
import com.example.facturacliente.models.entity.Product;

import java.util.List;

public interface IClientService {

    public List<Client> findAll();

    public void save(Client client);

    public Client findById(Long id);

    public void delete(Long id);

    public List<Product> findByName(String term);

    public void saveFactura(Factura factura);

    public Product findProductById(Long id);

    public Factura findFacturaById(Long id);

    public void deleteFactura(Long id);
}
