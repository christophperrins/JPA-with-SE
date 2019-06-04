package com.qa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.qa.domain.Account;


public class AccountRepositoryDB implements AccountRepository{

	public EntityManagerFactory  managerFactory = Persistence.createEntityManagerFactory("myPU");
	public EntityManager manager = managerFactory.createEntityManager();
	public EntityTransaction transaction = manager.getTransaction();

	public Account add(Account account) {
		transaction.begin();
		manager.persist(account);
		transaction.commit();
		return account;
	}
	
	public Account getOne(int id) {
		return manager.find(Account.class, id);
	}
	
	public List<Account> getAll() {
		TypedQuery<Account> query = manager.createQuery("SELECT acc from Account acc", Account.class);
		List<Account> list = query.getResultList();
		return list;
	}
		
	public Account updateAccount(int dbId, Account newInfo) {
		Account savedAccount = getOne(dbId);
		transaction.begin();
		savedAccount.setFirstName(newInfo.getFirstName());
		savedAccount.setLastName(newInfo.getLastName());
		savedAccount.setAccountNumber(newInfo.getAccountNumber());
		transaction.commit();
		return savedAccount;
	}

	public void deleteAccount(int id) {
		Account account = getOne(id);
		transaction.begin();
		manager.remove(account);
		transaction.commit();
	}

	
}
