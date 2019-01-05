package com.panjura.mosque.miyabarimosque.controller;

import static com.panjura.mosque.miyabarimosque.commons.CustomMessages.ERROR_MSG;
import static com.panjura.mosque.miyabarimosque.commons.CustomMessages.SUCCESS_MSG;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.panjura.mosque.miyabarimosque.bean.Income;
import com.panjura.mosque.miyabarimosque.service.GenericsDAOService;

@RestController
@RequestMapping("/income")
public class IncomeController {

	private static final String INCOME = "income";
	@Autowired @Qualifier("incomeService") GenericsDAOService<Income> incomeService;
	@Autowired private MessageSource msgSource ;
	
	@GetMapping
	public ResponseEntity<List<Income>> getAllIncomeInfo (final HttpServletRequest request) {
		List<Income> list = incomeService.getAllData();
		HttpHeaders headers = new HttpHeaders();
		final Locale locale = request.getLocale();
		String errorMsg = msgSource.getMessage("common.ListNotFoundMsg", new String[]{"Income List"}, locale);
		headers.add(ERROR_MSG, errorMsg);
		
		if (CollectionUtils.isEmpty(list)) {
			return new ResponseEntity<>(new ArrayList<>(),headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Income> saveIncomeInfo(@Valid @RequestBody Income income, Errors errors , final HttpServletRequest request) {
		final Locale locale = request.getLocale();
		HttpHeaders headers = new HttpHeaders();
		
		if (errors.hasErrors()) {
			headers.add(ERROR_MSG, msgSource.getMessage("common.saveErrorMsg", new String[]{INCOME, "Saved"}, locale));
				String errorResult = errors.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
			ResponseEntity.badRequest().body(errorResult);
			return ResponseEntity.noContent().headers(headers).build();
		}
		incomeService.saveOrUpdateData(income);
		if (income.getIncomeId() == null) {
			headers.add(SUCCESS_MSG, msgSource.getMessage("common.saveSuccessMsg", new String[]{INCOME, "Saved"}, locale));
		} else {
			headers.add(SUCCESS_MSG, msgSource.getMessage("common.saveSuccessMsg", new String[]{INCOME, "update"}, locale));
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(income.getIncomeId()).toUri();
		headers.setLocation(location);
		
		return ResponseEntity.created(location).headers(headers).build();
	} 
	
	@DeleteMapping("/{incomeId}")
	public ResponseEntity<Void> deleteIncomeHistory (@PathVariable Integer incomeId, final HttpServletRequest request) {
		final Locale locale = request.getLocale();
		Optional<Income> findUser = incomeService.findById(incomeId);
		HttpHeaders headers = new HttpHeaders();
		if(incomeId != null && findUser.isPresent()) {
			incomeService.deleteData(incomeId);
			headers.add(SUCCESS_MSG, msgSource.getMessage("common.deleteSuccessMsg", new Integer[]{incomeId}, locale));
			return ResponseEntity.noContent().headers(headers).build();
		}
		headers.add(ERROR_MSG, msgSource.getMessage("common.deleteFailedMsg ", new Integer[]{incomeId}, locale));
		return ResponseEntity.notFound().headers(headers).build();
	}

}
