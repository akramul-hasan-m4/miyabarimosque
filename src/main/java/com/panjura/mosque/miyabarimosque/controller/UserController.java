package com.panjura.mosque.miyabarimosque.controller;

import static com.panjura.mosque.miyabarimosque.commons.CustomMessages.ERROR_MSG;
import static com.panjura.mosque.miyabarimosque.commons.CustomMessages.SUCCESS_MSG;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.panjura.mosque.miyabarimosque.bean.User;
import com.panjura.mosque.miyabarimosque.service.GenericsDAOService;

@RestController
@RequestMapping("/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired @Qualifier("userService") GenericsDAOService<User> userService;
	@Autowired private MessageSource msgSource ;
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser (final HttpServletRequest request) {
		List<User> list = userService.getAllData();
		HttpHeaders headers = new HttpHeaders();
		final Locale locale = request.getLocale();
		logger.info("locale id  -> {}", locale);
		String errorMsg = msgSource.getMessage("common.ListNotFoundMsg", new String[]{"User List"}, locale);
		headers.add(ERROR_MSG, errorMsg);
		
		if (CollectionUtils.isEmpty(list)) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user, Errors errors , final HttpServletRequest request) {
		final Locale locale = request.getLocale();
		HttpHeaders headers = new HttpHeaders();
		
		if (errors.hasErrors()) {
			headers.add(ERROR_MSG, msgSource.getMessage("common.saveErrorMsg", new String[]{"User", "Saved"}, locale));
				String errorResult = errors.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
			ResponseEntity.badRequest().body(errorResult);
			return ResponseEntity.noContent().headers(headers).build();
		}
		userService.saveOrUpdateData(user);
		if (user.getUserId() == null) {
			headers.add(SUCCESS_MSG, msgSource.getMessage("common.saveSuccessMsg", new String[]{"User", "Saved"}, locale));
		} else {
			headers.add(SUCCESS_MSG, msgSource.getMessage("common.saveSuccessMsg", new String[]{"User", "update"}, locale));
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUserId()).toUri();
		headers.setLocation(location);
		
		return ResponseEntity.created(location).headers(headers).build();
	} 
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser (@PathVariable Integer userId, final HttpServletRequest request) {
		final Locale locale = request.getLocale();
		Optional<User> findUser = userService.findById(userId);
		HttpHeaders headers = new HttpHeaders();
		if(userId != null && findUser.isPresent()) {
			userService.deleteData(userId);
			headers.add(SUCCESS_MSG, msgSource.getMessage("common.deleteSuccessMsg", new Integer[]{userId}, locale));
			return ResponseEntity.noContent().headers(headers).build();
		}
		headers.add(ERROR_MSG, msgSource.getMessage("common.deleteFailedMsg ", new Integer[]{userId}, locale));
		return ResponseEntity.notFound().headers(headers).build();
	}

}
