package com.qa.accountTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.qa.domain.Account;
import com.qa.repository.AccountRepositoryDB;

public class AccountTest {

	Account account;
	AccountRepositoryDB repositoryDB;
	
	@Before
	public void setup() {
		account = new Account("Chris", "Perrins", 0);
		repositoryDB = new AccountRepositoryDB();
	}
	
	@Test
	public void createAccount() {
		String firstName = account.getFirstName();
		String lastName = account.getLastName();
		int accountNumber = account.getAccountNumber();
		assertEquals("Firstname not saved", "Chris", firstName);
		assertEquals("Lastname not saved", "Perrins", lastName);
		assertEquals("Accountnumber not saved", 0, accountNumber);
	}
	
	@Test
	public void persistAccount() {
		assertEquals(0, account.getId());
		repositoryDB.add(account);
		int id = account.getId();
		assertFalse("account id not given -> not persisted to database", id == 0);
	}
	
	@Test
	public void findAccount() {
		repositoryDB.add(account);
		Account returned = repositoryDB.getOne(account.getId());
		assertEquals("Name of returned not the same as saved", account.getFirstName(), returned.getFirstName());
	}
	
	@Test
	public void findAllAccounts() {
		int presize = repositoryDB.getAll().size();
		repositoryDB.add(account);
		repositoryDB.add(new Account("Corzaon", "Gaza", 125));
		int postsize = repositoryDB.getAll().size();
		assertEquals("FindAll Accounts not returning", presize + 2, postsize);
	}
	
	@Test
	public void updateAccount() {
		repositoryDB.add(account);
		Account newinfo = new Account("Corazon", "Gaza", 123);
		repositoryDB.updateAccount(account.getId(), newinfo);
		assertEquals("Corazon", account.getFirstName());
		assertEquals("Gaza", account.getLastName());
		assertEquals(123, account.getAccountNumber());	
	}
	
	@Test
	public void deleteAccount() {
		repositoryDB.add(account);
		assertFalse(account.getId() == 0);
		repositoryDB.deleteAccount(account.getId());
		Account returnedAccount = repositoryDB.getOne(account.getId());
		assertNull(returnedAccount);
	}
}
