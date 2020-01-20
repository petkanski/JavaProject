package com.devision.javaProject.repositories;

import com.devision.javaProject.models.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface PlotRepository extends JpaRepository<Plot, Long> {
}
