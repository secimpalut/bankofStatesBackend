package com.bank.controller;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bank.request.TransactionRequest;
import com.bank.response.TransactionResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/account")
public class AccountController {
	@PostMapping("/deposit")
	private ResponseEntity<TransactionResponse> deposit(@Valid @RequestBody TransactionRequest request) {
		TransactionResponse reponse = new TransactionResponse();		
		return new ResponseEntity<>(reponse, HttpStatus.OK);
	}
}