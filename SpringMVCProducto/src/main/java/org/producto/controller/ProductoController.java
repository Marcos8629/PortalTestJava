package org.producto.controller;

import java.util.Map;

import org.springframework.ui.Model;
import org.producto.modelo.Producto;
import org.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ProductoController {
	@Autowired
	private ProductoService service;
	
	@RequestMapping("/index")
	public String listProducto (Map<String, Object> map) {
		map.put("producto", new Producto());
		map.put("productoList", service.listProducto());
		return "productoForm";
	}
	
	@RequestMapping(value="/producto/add", method=RequestMethod.POST)
	public String addProducto(@ModelAttribute Producto producto, BindingResult result) {
		service.addProducto(producto);
		return "redirect:/index";
	}
	@RequestMapping(value = "/edit/{id}")
	public String editProducto (@PathVariable("id") Integer id, Model model) {
		model.addAttribute("producto", service.getProductoById(id));
		model.addAttribute("productoList",service.listProducto());
		return "productoForm";
	}
	@RequestMapping(value = "/delete/{id}")
	public String deleteProducto (@PathVariable("id") Integer id) {
		service.deleteProducto(id);
		return "redirect:/index";
	}
}
