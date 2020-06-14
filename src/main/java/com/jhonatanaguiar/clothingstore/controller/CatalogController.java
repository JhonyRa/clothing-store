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
import com.jhonatanaguiar.clothingstore.model.Category;

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
		String[] categoryCodes = request.getParameterValues("category");
		
		//instanciando uma lista de categoria, onde a variavel category do tipo list recebe uma lista.
		List<Category> category =  new ArrayList<>();
		
		Category menClothe = new Category();
		menClothe.setName("Roupa Masculina");
		menClothe.setCode(1);
		
		Category womenClothe = new Category();
		womenClothe.setName("Roupa Feminina");
		womenClothe.setCode(2);
		
		Category tennis = new Category();
		tennis.setName("Tenis");
		tennis.setCode(3);
		
		Category others = new Category();
		others.setName("Outros");
		others.setCode(3);
		
		category.add(menClothe);
		category.add(womenClothe);
		category.add(tennis);
		category.add(others);
		
		
		// Envia as roupas do catalogo para o front
		request.setAttribute("clothes", catalogBean.getFilteredClothes(categoryCodes));
		request.setAttribute("categories", category);
		
		// Usuario clicou em adicionar
		if (request.getParameter("add") != null) {
			
			HttpSession session = request.getSession();
	
			// Verifica se ja existe um cart e cria um, caso contrario
			if (session.getAttribute("cart") == null) {
				// Caso nao exista a lista de compras, cria uma
				
				List<ClotheBean> cart = new ArrayList<>();
				session.setAttribute("cart", cart);
			}
			
			//Recupera a lista de compras
			List<ClotheBean> cart = (List<ClotheBean>) session.getAttribute("cart");
			
			// Identifica o codigo da roupa que o usuario clicou
			String codeString = request.getParameter("add");
			Integer code = Integer.parseInt(codeString);
			
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
		request.getRequestDispatcher("/catalog.jsp").forward(request, response);
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
