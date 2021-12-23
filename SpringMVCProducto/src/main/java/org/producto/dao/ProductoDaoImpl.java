package org.producto.dao;



import java.util.List;

import org.hibernate.SessionFactory;
import org.producto.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoDaoImpl implements ProductoDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public void addProducto(Producto producto) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(producto);
		} catch (Exception e) {
			System.out.println("Exception add: " + e.getLocalizedMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> listProducto() {
		List<Producto> list = null;
		try {
			list = (List<Producto>) sessionFactory.getCurrentSession().createQuery("from Producto").list();
		} catch (Exception e) {
			System.out.println("Exception list: " + e.getMessage());
		}
		return list;
	}

	@Override
	public Producto getProductoById(Integer id) {
		Producto producto = null;
		try {
			producto = (Producto) sessionFactory.getCurrentSession().get(Producto.class, id);
		} catch (Exception e) {
			System.out.println("Exception get: " + e.getMessage());
		}
		return producto;
	}

	@Override
	public void deleteProducto(Integer id) {
		Producto producto = null;
		try {
			producto = (Producto) sessionFactory.getCurrentSession().load(Producto.class, id);
			sessionFactory.getCurrentSession().delete(producto);
		} catch (Exception e) {
			System.out.println("Exception delete: " + e.getMessage());
		}
	}
	
}
