package com.qa.repository;

import com.qa.domain.Account;

public interface AccountRepository {

	public void add(Account account);
	
	public Account retrieve(int id);
}
