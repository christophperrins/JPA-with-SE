package com.qa.accountTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.qa.domain.Account;
import com.qa.repository.AccountRepositoryDB;
import com.qa.repository.AccountRepositoryMap;

public class AccountTest {

	@Test
	public void accountTest() {
		Account account = new Account();
		account.setFirstName("Chris");
		account.setLastName("Perrins");
		account.setAccountNumber(0);
		String firstName = account.getFirstName();
		String lastName = account.getLastName();
		int accountNumber = account.getAccountNumber();
		assertEquals("Chris", firstName);
		assertEquals("Perrins", lastName);
		assertEquals(0, accountNumber);
	}
	
	@Test
	public void managerTest() {
		AccountRepositoryMap accountManager = new AccountRepositoryMap();
		Account account = new Account();
		accountManager.add(account);
		Account returnedAccount = accountManager.retrieve(0);
		assertEquals(account, returnedAccount);
	}
	
	@Test
	public void databaseTest() {
		AccountRepositoryDB repositoryDB = new AccountRepositoryDB();
		Account account = new Account();
		repositoryDB.add(account);	
		int id = account.getId();
		Account a = repositoryDB.retrieve(id);

		assertEquals(account, a);
	}
}
