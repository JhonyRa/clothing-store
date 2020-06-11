package com.jhonatanaguiar.clothingstore.business.bean;

import lombok.Data;

@Data
public class ClotheBean {
	
	private Integer code;
	private String name;
	private Integer codeCategory;
	private String category;
	private Float price;
	private String imageName;
	
}