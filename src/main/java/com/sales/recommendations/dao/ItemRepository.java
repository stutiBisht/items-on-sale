package com.sales.recommendations.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sales.recommendations.model.Item;
import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

	/*
	 * Using JPA native query to fetch all records on the basis of certain criteria.
	 * Query is explained in depth in below comments. This method can be refactored to use
	 * core JPA and HQL features but not able to do due to being new to JPA
	 */
	@Query(value = "select item.item_id, item.item_name,item.category_id, item.on_sale, item.price, ord from item,(select item.item_id, ord from item,(select category_id, avg(rating)+20 as ord from  item_review\n"
			+ "	group by category_id, user_id having user_id = ?1 ) as cat where item.category_id = cat.category_id and item.on_sale = 'Y' union\n"
			+ "	select item.item_id, ord from item,(select  item.category_id as category_id, 10 as ord from user_favorite, item where user_favorite.item_id = item.item_id\n"
			+ "	and item.category_id not in (select order_detail.category_id from order_detail where order_detail.user_id = ?1) )as cat where item.category_id =cat.category_id\n"
			+ "	and  item.on_sale = 'Y' union select item.item_id ,ord from item, (select category_id, avg(rating) as ord from  item_review group by category_id\n"
			+ "	having category_id not in (select category_id from  item_review group by category_id,user_id having user_id = ?1) and category_id not in (select  item.category_id as category_id\n"
			+ "	from user_favorite, item where user_favorite.item_id = item.item_id) ) as cat where item.category_id = cat.category_id and item.on_sale = 'Y'\n"
			+ "	) as itm where item.item_id = itm.item_id order by ord desc", nativeQuery = true)
	List<Item> getRecommendedItemsByCatRatings(Long userId);

	
	
	//List<Item> updateItemOnsale(String items);
	
	
	/*
	 * "select item.item_id, item.item_name, ord\r\n" +
	 * "  from item,(select item.item_id, ord\r\n" +
	 * "          from item,(select category_id, avg(rating)+20 as ord  --> it will return categories with highest rating which are rated by current user\r\n"
	 * + "                       from  item_review\r\n" +
	 * "	           group by category_id, user_id\r\n" +
	 * "                     having user_id = 5 ) as cat\r\n" +
	 * "         where item.category_id = cat.category_id\r\n" +
	 * "           and     item.on_sale = 'Y'\r\n" + "        union\r\n" +
	 * "	select item.item_id, ord\r\n" +
	 * "	  from item,(select  item.category_id as category_id, 10 as ord  --> It will return categories which are in users wishlist\r\n"
	 * + "                       from user_favorite, item\r\n" +
	 * "                      where user_favorite.item_id = item.item_id\r\n" +
	 * "	                and item.category_id not in (select order_detail.category_id   --> 'not in' condition to exclude categories which are already rated by user\r\n"
	 * + "			                               from order_detail\r\n" +
	 * "			                              where order_detail.user_id = 5 )\r\n"
	 * + "	            )as cat\r\n" +
	 * "	  where item.category_id =cat.category_id\r\n" +
	 * "            and     item.on_sale = 'Y'\r\n" + "	union\r\n" +
	 * "        select item.item_id ,ord\r\n" +
	 * "	  from item, (select category_id, avg(rating) as ord   --> It will return categories which are highest rated categories within other users\r\n"
	 * + "                        from  item_review\r\n" +
	 * "                    group by category_id\r\n" +
	 * "                      having category_id not in (select category_id       -->'not in' condition to exclude categories which are already rated by user\r\n"
	 * + "                                                   from  item_review\r\n"
	 * +
	 * "                                               group by category_id,user_id\r\n"
	 * + "                                                 having user_id = 5)\r\n"
	 * +
	 * "		         and category_id not in (select  item.category_id as category_id -->'not in' condition to exclude categories which are already in users wishlist\r\n"
	 * +
	 * "                                                   from user_favorite, item\r\n"
	 * +
	 * "                                                  where user_favorite.item_id = item.item_id)\r\n"
	 * + "		      ) as cat\r\n" +
	 * "	 where item.category_id = cat.category_id\r\n" +
	 * "           and     item.on_sale = 'Y'\r\n" + "	  ) as itm\r\n" +
	 * "where item.item_id = itm.item_id\r\n" + "  order by ord desc"
	 */

}
