package eu.margiel.repositories;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.synyx.hades.domain.Specification;

import eu.margiel.domain.News;

public class PublishedNews implements Specification<News> {
	@Override
	public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return builder.equal(root.get("published"), true);
	}
}