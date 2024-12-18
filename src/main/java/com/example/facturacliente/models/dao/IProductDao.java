package com.example.facturacliente.models.dao;

import com.example.facturacliente.models.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductDao extends CrudRepository<Product, Long> {

    @Query("select p from Product p where p.name like %?1%")
    public List<Product> findByName(String term);

    public List<Product> findByNameLikeIgnoreCase(String term);
}
