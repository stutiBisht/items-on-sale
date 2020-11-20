package com.sales.recommendations.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue
	private Long itemId;

	@Column(name = "itemName")
	@NotEmpty
	private String itemName;

	@NotEmpty
	@Column(name = "categoryId")
	private Long categoryId;

	@NotEmpty
	@Column(name = "price")
	private Double price;

	@NotEmpty
	@Column(name = "on_sale")
	private Character onsale;

	

	public Item(Long itemId, @NotEmpty String itemName, @NotEmpty Long categoryId, @NotEmpty Double price,
			@NotEmpty Character onsale) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.categoryId = categoryId;
		this.price = price;
		this.onsale = onsale;
	}

	

	public Item() {
	}



	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Character getOnsale() {
		return onsale;
	}

	public void setOnsale(Character onsale) {
		this.onsale = onsale;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", categoryId=" + categoryId + ", price=" + price
				+ ", onsale=" + onsale + "]";
	}

	

}
