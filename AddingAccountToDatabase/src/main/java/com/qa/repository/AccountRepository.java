package com.qa.repository;

import java.util.List;

import com.qa.domain.Account;

public interface AccountRepository {

	// C
	public Account add(Account account);
	
	// R
	public Account getOne(int id);
	public List<Account> getAll();
	
	// U
	public Account updateAccount(int dbId, Account newInfo);
	
	// D
	public void deleteAccount(int id);

}
