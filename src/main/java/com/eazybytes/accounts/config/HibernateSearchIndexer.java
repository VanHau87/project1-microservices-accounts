package com.eazybytes.accounts.config;

import org.hibernate.Session;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.springframework.stereotype.Service;

import com.eazybytes.accounts.model.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class HibernateSearchIndexer {
	@PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void startMassIndexer() throws InterruptedException {
        Session session = entityManager.unwrap(Session.class);
        MassIndexer indexer = Search.session(session)
                .massIndexer(Customer.class)
                .threadsToLoadObjects(4);
        indexer.startAndWait();
    }
}
