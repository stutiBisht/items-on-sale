package com.sales.recommendations.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales.recommendations.common.ApplicationValidationException;
import com.sales.recommendations.model.Item;
import com.sales.recommendations.service.ItemService;

@CrossOrigin(origins = "rbc.shopping.com", maxAge = 3600) // using to allow only mentioned domain
@RequestMapping("/recommendations")
@RestController
public class ItemController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	ItemService itemService;

	// Controller method to get list of recommended item from DB
	@GetMapping(path = "{userId}")
	public ResponseEntity<List<Item>> getRecommendedItems(@PathVariable Long userId)
			throws ApplicationValidationException {
		logger.info("calling getRecommendedItems method of ItemController for userId-->" + userId);

		List<Item> itemList = itemService.getRecommendedItemsByCatRatings(userId);
		return new ResponseEntity<List<Item>>(itemList, HttpStatus.OK);
	}
}