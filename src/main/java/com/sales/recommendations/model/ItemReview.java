package com.sales.recommendations.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "item_review")
public class ItemReview {

	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "itemId")
	private Long itemId;

	@Min(0)
	@Max(10)
	@Column(name = "rating")
	private Integer rating;

	@Column(name = "userId")
	private String userId;

	public Long getItemId() {
		return itemId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ItemReview [itemId=" + itemId + ", rating=" + rating + ", userId=" + userId + "]";
	}

}
	  
	  
