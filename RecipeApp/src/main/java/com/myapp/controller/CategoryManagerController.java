package com.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.entity.Category;
import com.myapp.entity.Recipe;
import com.myapp.exception.handler.AppServiceException;
import com.myapp.service.CatagoryService;

/**
 * Rest controller for handling all the operations related to categories
 * 
 * @author Madhusudan
 * @version : 1.0.0
 * 
 *REST URLS:
 *
 *1. GET :/categories -> to retrieve all the categories
 *2. GET :/categories/{description} -> to retrieve recipe of a given category
 *
 */

@RestController
public class CategoryManagerController {

	@Autowired
	private CatagoryService catagoryService;
	
	/**
	 * This Rest controller is used to fetch all the categories in the application
	 * 
	 * @return list of categories if exist or No data found exception
	 * @throws AppServiceException
	 */
	@RequestMapping(value = "categories", method = RequestMethod.GET, headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Category> findAllCategories() throws AppServiceException {

		try {
			return catagoryService.getAllCatogory();
		} catch (AppServiceException ex) {
			throw ex;
		}

	}
	
	/**
	 * This method is use to get the recipes by providing the category ID
	 * 
	 * @param category ID
	 * @return the recipes if found else will return Resource not found
	 *         exception
	 * @throws AppServiceException
	 */
	@RequestMapping(value = "categories/{catId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Recipe> findByCategory(@PathVariable Long catId) throws AppServiceException {
		
		try {
			return catagoryService.getByCatagory(catId);
		} catch (AppServiceException ex) {
			throw ex;
		}
	}

}
