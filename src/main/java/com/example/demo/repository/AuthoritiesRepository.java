package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Authorities;

//管理者アカウントのRepository
public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {

}
