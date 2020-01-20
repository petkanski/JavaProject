package com.devision.javaProject.repositories;

import com.devision.javaProject.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(@Param("name") String username);
}
