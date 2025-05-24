package com.eazybytes.accounts.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.search.engine.search.query.dsl.SearchQueryOptionsStep;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.search.loading.dsl.SearchLoadingOptionsStep;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.eazybytes.accounts.model.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerSearchService {
	@PersistenceContext
	private final EntityManager entityManager;
	
	public Page<Customer> search(String keyword, int page, int size) {
		Session session = entityManager.unwrap(Session.class);
        SearchSession searchSession = Search.session(session);
        SearchQueryOptionsStep<?, Customer, SearchLoadingOptionsStep, ?, ?> searchQuery = searchSession.search(Customer.class)
                .where(f -> f.bool().with(b -> {
                    b.should(f.match()
                        .field("name")
                        .matching(keyword)
                        .fuzzy(1));
                    b.should(f.match()
                        .field("email")
                        .matching(keyword)
                        .fuzzy(1));
                    b.minimumShouldMatchNumber(1);
                }));
        long totalHitCount = searchQuery.fetchTotalHitCount();
        List<Customer> hits = searchQuery.fetchHits(page * size, size);

        return new PageImpl<>(hits, PageRequest.of(page, size), totalHitCount);
    }
}
