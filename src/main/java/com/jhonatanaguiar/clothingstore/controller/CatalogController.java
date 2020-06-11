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

import com.jhonatanaguiar.clothingstore.business.bean.CatalogBean;
import com.jhonatanaguiar.clothingstore.business.bean.ClotheBean;
import com.jhonatanaguiar.clothingstore.business.enums.CategoryEnum;

/**
 * Servlet implementation class CatalogController
 */
@WebServlet("/catalog")
public class CatalogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CatalogBean catalogBean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogController() {
        super();
        
     // Inicia o catalogo com as roupas padrao
     		catalogBean = new CatalogBean();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recupera os valores do select do front
		String[] categoryCodes = request.getParameterValues("categoria");

		// Envia as roupas do catalogo para o front
		request.setAttribute("roupas", catalogBean.getFilteredClothes(categoryCodes));
		request.setAttribute("categorias", CategoryEnum.values());
		
		// Usuario clicou em adicionar
		if (request.getParameter("adicionar") != null) {
			
			HttpSession session = request.getSession();
	
			// Verifica se ja existe um cart e cria um, caso contratio
			if (session.getAttribute("cart") == null) {
				// Caso nao exista a lista de compras, cria uma
				
				List<ClotheBean> cart = new ArrayList<>();
				session.setAttribute("cart", cart);
			}
			
			//Recupera a lista de compras
			List<ClotheBean> cart = (List<ClotheBean>) session.getAttribute("cart");
			
			// Identifica o codigo da roupa que o usuario clicout
			String codigoString = request.getParameter("adicionar");
			Integer code = Integer.parseInt(codigoString);
			
			// Percorre todas as roupas e procure aquela com codigo igual.
			// Adiciona ao carrinho
			List<ClotheBean> allClothes = catalogBean.getClothes();
			for (ClotheBean clothe : allClothes) {
				if (clothe.getCode().equals(code)) {
					cart.add(clothe);
				}
			}
			
		}

		// Envia a pagina jsp na requisicao
		request.getRequestDispatcher("/catalogo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
