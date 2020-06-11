package com.jhonatanaguiar.clothingstore.business.bean;

import java.util.ArrayList;
import java.util.List;

import com.jhonatanaguiar.clothingstore.business.enums.CategoryEnum;

import lombok.Data;

@Data
public class CatalogBean {
	
	private List<ClotheBean> clothes;

	public CatalogBean() {
		// Inicia a lista de roupas com algumas roupas
		clothes = new ArrayList<>();
		clothes.add(newClothe("Camiseta", CategoryEnum.MEN_CLOTHE, 49.9f, "a-definir.jpg"));
		clothes.add(newClothe("Calça", CategoryEnum.MEN_CLOTHE, 80f, "a-definir.jpg"));
		clothes.add(newClothe("a definir", CategoryEnum.WOMEN_CLOTHE, 15f, "a-definir.jpg"));
		clothes.add(newClothe("a definir", CategoryEnum.WOMEN_CLOTHE, 120f, "a-definir.jpg"));
		clothes.add(newClothe("a definir", CategoryEnum.WOMEN_CLOTHE, 70.99f, "a-definir.jpg"));
		clothes.add(newClothe("a definir", CategoryEnum.TENNIS, 80f, "a-definir.jpg"));
		clothes.add(newClothe("a definir", CategoryEnum.TENNIS, 45.99f, "a-definir.jpg"));
	}

	// Funcao auxiliar para criar novas roupas
	private ClotheBean newClothe(String name, CategoryEnum categoryEnum, Float price, String imageName) {

		// Calcula o codigo de acordo com o tamanho da lista de roupas
		Integer code = clothes.size();

		ClotheBean clotheBean = new ClotheBean();
		clotheBean.setCode(code);
		clotheBean.setName(name);
		clotheBean.setCodeCategory(categoryEnum.getCode());
		clotheBean.setCategory(categoryEnum.getName());
		clotheBean.setPrice(price);
		clotheBean.setImageName(imageName);

		return clotheBean;
	}
	
//	public List<ClotheBean> getClothes() {
//		return clothes;
//	}
//
//	public void setClothes(List<ClotheBean> clothes) {
//		this.clothes = clothes;
//	}

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
