package com.sales.recommendations.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user_favorite")
public class UserFavorite {
	

	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	@Column(name = "userId")
	private Long userId;

	@NotEmpty
	@Column(name = "itemId")
	private Long itemId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "UserFavs [userId=" + userId + ", itemId=" + itemId + "]";
	}

	

	
	

	



}
