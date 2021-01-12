package com.bank.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.request.SignUpForm;
import com.bank.response.Response;



@RestController("/auth")
public class LoginController {
	
	@PostMapping("/register")
	public ResponseEntity<Response> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		return null;
	}
    

}
