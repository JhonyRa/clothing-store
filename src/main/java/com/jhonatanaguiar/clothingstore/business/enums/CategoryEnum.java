package com.jhonatanaguiar.clothingstore.business.enums;

public enum CategoryEnum {
	
	// Criamos objetos do enum seguindo o construtor privado
	MEN_CLOTHE("Roupa Masculina", 0), WOMEN_CLOTHE("Roupa Feminina", 1),
	TENNIS("Tenis", 2);

	private String name;
	private Integer code;

	// Construtor do enum
	private CategoryEnum(String name, Integer code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}
}
