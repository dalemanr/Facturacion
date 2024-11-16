package com.example.facturacliente.service;

import com.example.facturacliente.models.dao.IClientDao;
import com.example.facturacliente.models.dao.IFacturaDao;
import com.example.facturacliente.models.dao.IProductDao;
import com.example.facturacliente.models.entity.Client;
import com.example.facturacliente.models.entity.Factura;
import com.example.facturacliente.models.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService implements IClientService {

    @Autowired
    private IClientDao clientDao;

    @Autowired
    private IProductDao productDao;

    @Autowired
    private IFacturaDao facturaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) clientDao.findAll();
    }

    @Override
    @Transactional
    public void save(Client client) {
        clientDao.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public Client findById(Long id) {
        return clientDao.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientDao.deleteById(id);
    }

    @Override
    @Transactional
    public List<Product> findByName(String term) {
        return productDao.findByNameLikeIgnoreCase("%"+term+"%");
    }

    @Override
    @Transactional
    public void saveFactura(Factura factura) {
        facturaDao.save(factura);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findProductById(Long id) {
        return productDao.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaById(Long id) {
        return facturaDao.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void deleteFactura(Long id) {
        facturaDao.deleteById(id);
    }
}
