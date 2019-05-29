package com.qa.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import com.qa.domain.Account;


public class AccountRepositoryDB implements AccountRepository{

	public EntityManagerFactory  managerFactory = Persistence.createEntityManagerFactory("myPU");
	public EntityManager manager = managerFactory.createEntityManager();
	public EntityTransaction transaction = manager.getTransaction();
	
	public void add(Account account) {
		transaction.begin();
		manager.persist(account);
		transaction.commit();
	}

	public Account retrieve(int id) {
		return manager.find(Account.class, id);
	}

}
