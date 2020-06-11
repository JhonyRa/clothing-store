package com.jhonatanaguiar.clothingstore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jhonatanaguiar.clothingstore.business.bean.ClotheBean;

@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public CartController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Recupera o httpSession
		HttpSession session = request.getSession();
		
		// Verifica se ja existe um cart e cria um, caso contratio
		if (session.getAttribute("cart") == null) {
			// Caso nao exista a lista de compras, cria uma
			
			List<ClotheBean> cart = new ArrayList<>();
			session.setAttribute("cart", cart);
		}
		
		//Recupera a lista de compras
		List<ClotheBean> cart = (List<ClotheBean>) session.getAttribute("cart");
		
		// Envia o cart para o front
		request.setAttribute("cart", cart);
		
		// Soma os valores e enviar para o jsp
		Float total = 0f;
		for (ClotheBean roupa : cart) {
			total += roupa.getPrice();
		}
		request.setAttribute("total", total);
		
		// Envia a pagina jsp na requisicao
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Usuario clicou em remover
		String indexString = request.getParameter("remover");
		int index = Integer.parseInt(indexString) - 1; // Count e 1-based
		
		// Recupera o cart
		HttpSession session = request.getSession();
		List<ClotheBean> cart = (List<ClotheBean>) session.getAttribute("cart");
		
		// Remove a roupa do carrinho pelo indice
		cart.remove(index);
		
		doGet(request, response);
	}

}
