package com.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.report.model.CatalogoNetflix;

public interface CatalogoNetflixRepository extends JpaRepository<CatalogoNetflix, Long> {

	List<CatalogoNetflix> findByReleaseYear(final Integer releaseYear);

}