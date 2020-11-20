package com.sales.recommendations.controller;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.sales.recommendations.model.Item;
import com.sales.recommendations.service.ItemService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@ActiveProfiles(value = "test")
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;
    
    @Before
	public void setUp() {
		Mockito.reset(itemService);
	}

    @Test
    public void providesRestEndpointAndReturnItemBasedOnServiceLayer() throws Exception {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(2L, "test", 3l, 12.0, 'Y'));
        when(itemService.getRecommendedItemsByCatRatings(1L)).thenReturn(itemList);
        mockMvc.perform(get("/recommendations/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].itemId", is(2)))
		.andExpect(jsonPath("$[0].itemName", is("test")));


    }
}