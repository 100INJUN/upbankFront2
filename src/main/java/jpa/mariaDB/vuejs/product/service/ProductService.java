package jpa.mariaDB.vuejs.product.service;

import java.util.List;

import jpa.mariaDB.vuejs.product.vo.Product;

public interface ProductService {

	public List<Product> listAll();  // list
	public Product save(Product product);// insert
	public Product get(int id);		// 1건 select
	public void delete(int id);		// 1건 delete
}
