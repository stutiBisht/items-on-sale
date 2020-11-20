package com.sales.recommendations.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sales.recommendations.common.ApplicationValidationException;
import com.sales.recommendations.dao.ItemRepository;
import com.sales.recommendations.model.Item;


@Service
public class ItemService {

	private final Logger logger = LogManager.getLogger(this.getClass());
	
	
	@Autowired
    private ItemRepository itemRepository;
	
	// this method will fetch list of recommended items on the basis higher to lower ranking of categories those belong to
	public List<Item> getRecommendedItemsByCatRatings(Long userId) throws ApplicationValidationException {
		List<Item> itemList = new ArrayList<Item>();
		logger.info("calling getRecommendedItemsByCatRatings method of ItemService for userId-->" + userId);
		
		itemList = itemRepository.getRecommendedItemsByCatRatings(userId);
		logger.info("itemList returned from DB is--> " + itemList);
		if (itemList.isEmpty()) {
			logger.error("No record found for userId-->" + userId);
			throw new EntityNotFoundException("RecommendationItemList not found at this time for userId-->" + userId);
		}
		return itemList;
	}
	
	// to schedule job for 5AM daily
	@Scheduled(cron = "0 0 5 * * *") 
	public void scheduleTaskUsingCronExpression() {
		String items = "";
		long now = System.currentTimeMillis() / 1000;
		logger.info("schedule tasks using cron jobs - " + now);
		//itemRepository.updateItemOnsale(items);

	}
}
