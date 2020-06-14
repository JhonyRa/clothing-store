package com.jhonatanaguiar.clothingstore.business.bean;

import java.util.ArrayList;
import java.util.List;

import com.jhonatanaguiar.clothingstore.model.Category;

import lombok.Data;

@Data
public class CatalogBean {
	
	
	private List<ClotheBean> clothes;
	
	
	public CatalogBean() {
		
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

		
		
		// Inicia a lista de roupas com algumas roupas
		clothes = new ArrayList<>();
		clothes.add(newClothe("Camiseta de time", menClothe, 179.99f, "camisa-barcelona-masculina-img.jpg"));
		clothes.add(newClothe("Calça", menClothe, 49.9f, "calca-masculina-img.jpg"));
		clothes.add(newClothe("Agasalho", womenClothe, 249.9f, "agasalho-feminino-img.jpg"));
		clothes.add(newClothe("Camiseta", womenClothe, 119.9f, "camiseta-oxer-cord-ii-feminina-img.jpg"));
		clothes.add(newClothe("Maiô", womenClothe, 70.99f, "maio-para-natacao-img.jpg"));
		clothes.add(newClothe("Tênis", tennis, 349.9f, "tenis-masculino-img.jpg"));
		clothes.add(newClothe("Tênis", tennis, 249.9f, "tenis-nike-air-img.jpg"));
	}

	// Funcao auxiliar para criar novas roupas
	private ClotheBean newClothe(String name, Category category, Float price, String imageName) {

		// Calcula o codigo de acordo com o tamanho da lista de roupas
		Integer code = clothes.size();

		ClotheBean clotheBean = new ClotheBean();
		clotheBean.setCode(code);
		clotheBean.setName(name);
		clotheBean.setCodeCategory(category.getCode());
		clotheBean.setCategory(category.getName());
		clotheBean.setPrice(price);
		clotheBean.setImageName(imageName);

		return clotheBean;
	}

	// Retorna roupas filtradas de acordo com um codigo informado pelo navegador
	public List<ClotheBean> getFilteredClothes(String[] categoryCodes) {

		// Pessoa ainda nao escolheu o filtro
		if (categoryCodes == null) {
			return clothes;
		}

		List<ClotheBean> filteredClothes = new ArrayList<>();

		// Cria uma lista vazia de inteirs
		List<Integer> codes = new ArrayList<>();

		// Percorre os codigos informados pelo usuario, converte para inteiro e adiciona
		// na lista de codigos
		for (String categoryCode : categoryCodes) {
			codes.add(Integer.parseInt(categoryCode));
		}

		// Compara o codigo da roupa com o codigo que veio na requisicao
		for (ClotheBean clothe : clothes) {
			if (codes.contains(clothe.getCodeCategory())) {
				filteredClothes.add(clothe);
			}
		}

		return filteredClothes;

	}

}
