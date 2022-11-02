package jpa.mariaDB.vuejs.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import jpa.mariaDB.vuejs.product.vo.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
