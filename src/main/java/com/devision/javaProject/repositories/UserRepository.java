package com.devision.javaProject.repositories;

import com.devision.javaProject.models.User;
import com.devision.javaProject.models.projections.UserDetailView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(excerptProjection = UserDetailView.class)
public interface UserRepository extends JpaRepository<User, Long > {

    Page<User> findAllByAge(int age, Pageable page);

    Optional<User> findByUsername(@Param("username") String username);

}
