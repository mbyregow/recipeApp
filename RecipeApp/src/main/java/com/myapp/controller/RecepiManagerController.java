/**
 * 
 */
package com.myapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.entity.Recipe;
import com.myapp.exception.handler.AppServiceException;
import com.myapp.exception.handler.ResourceException;
import com.myapp.service.RecipeManagerService;

/**
 * Rest controller for handling all the operations related to Recipe
 * 
 * @author Madhusudan
 * @version : 1.0.0
 * 
 *REST URLS:
 *
 *1. GET :/recipe/{title} -> to retrieve recipe of a given title
 *2. POST:/recipe -> to create new recipe 
 *3. GET :/recipe -> to retrieve information of all the recipe
 *
 */
@RestController
public class RecepiManagerController {

	@Autowired
	private RecipeManagerService recepiManagerService;
	
	/**
	 * This Rest controller is used to retrieve recipe based on title
	 * 
	 * @param title
	 * @return
	 * @throws AppServiceException
	 * @throws ResourceException
	 */
	@RequestMapping(value = "recipes/{title}", method = RequestMethod.GET, headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public Recipe findByName(@PathVariable String title) throws AppServiceException,ResourceException{
		
		try {
			return recepiManagerService.getByName(title);
		} catch (NumberFormatException | ResourceException | AppServiceException ex) {
			throw ex;
		}
		
	}

	/**
	 * This Rest controller is used to create new recipe
	 * 
	 * @param recipe
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "recipes", method = RequestMethod.POST, headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public Recipe createNewRecipe(@Valid @RequestBody Recipe recipe) throws ResourceException, AppServiceException {
		
		try {
			return recepiManagerService.createNewRecepi(recipe);
		} catch (AppServiceException | ResourceException ex) {
			throw ex;
		}
		
	}

	/**
	 * This Rest controller is used to fetch all the recipe
	 * 
	 * @return list of recipes if exist or No data found exception
	 * @throws ResourceException
	 * @throws AppServiceException
	 */
	@RequestMapping(value = "recipes", method = RequestMethod.GET, headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Recipe> findAllRecipes() throws ResourceException, AppServiceException {

		try {
			return recepiManagerService.findAllRecipe();
		} catch (AppServiceException | ResourceException ex) {
			throw ex;
		}

	}

}
