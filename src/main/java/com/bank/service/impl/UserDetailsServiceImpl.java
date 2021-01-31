package com.bank.service.impl;

import static com.bank.util.DateUtil.SIMPLE_DATE_TIME_FORMAT;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.dao.TransactionDAO;
import com.bank.dao.UserDAO;
import com.bank.model.Transaction;
import com.bank.model.User;
import com.bank.model.UserRole;
import com.bank.repository.UserRepo;
import com.bank.service.UserService;
import static com.bank.util.DateUtil.getDateAsString;
@Service
public class UserDetailsServiceImpl implements UserDetailsService , UserService {

	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username)
            	.orElseThrow(() -> 
                    new UsernameNotFoundException("User Not Found with  username  " + username)
    );
    return user;
	}

	@Override
	public UserDAO getUserDAO(User user) {
		UserDAO userDAO = new UserDAO();
		userDAO.setUserId(user.getUserId());
		userDAO.setUsername(user.getUsername());
		userDAO.setFirstName(user.getFirstName());
		userDAO.setLastName(user.getLastName());
		userDAO.setEmail(user.getEmail());
		userDAO.setPhone(user.getPhone());
		Boolean isAdmin = user.getUserRoles().stream().
				filter(role -> role.getRole().getName().equals("admin")).findAny().isPresent();
		userDAO.setIsAdmin(isAdmin);
		if (user.getAccount() != null) {
			userDAO.setAccountNumber(user.getAccount().getAccountNumber());
			userDAO.setAccountBalance(user.getAccount().getAccountBalance());
			
			List<TransactionDAO> transactions = user.getAccount()
					.getTransactions().stream()
					.map(this::getTransactionDAO) //Method Reference
					.collect(Collectors.toList());
			userDAO.setTransactions(transactions);
			
			
		}
		return userDAO;
	}
	
	private TransactionDAO getTransactionDAO(Transaction transaction) {
		TransactionDAO transactionDAO = new TransactionDAO();
		transaction.setId(transaction.getId());
		transactionDAO.setDate(getDateAsString(transaction.getDate(), SIMPLE_DATE_TIME_FORMAT));
		transactionDAO.setAmount(transaction.getAmount());
		transactionDAO.setAvailableBalance(transaction.getAvailableBalance());
		transactionDAO.setDescription(transaction.getDescription());
		transactionDAO.setType(transaction.getType());
		transactionDAO.setIsTransfer(transaction.getIsTransfer());
		return transactionDAO;
	}
}
