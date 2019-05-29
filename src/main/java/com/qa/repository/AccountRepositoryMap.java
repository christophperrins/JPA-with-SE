package com.qa.repository;

import java.util.HashMap;

import com.qa.domain.Account;

public class AccountRepositoryMap implements AccountRepository {

	private HashMap<Integer, Account> map = new HashMap<Integer, Account>();
	
	public void add(Account account) {
		map.put(account.getId(), account);
	}

	public Account retrieve(int id) {
		return map.get(id);
	}
	
	public HashMap<Integer, Account> getMap() {
		return map;
	}

}
