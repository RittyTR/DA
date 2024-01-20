package com.example.BasicSpringBoot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
}

/*
Author - Rity Tharakkal Raphel
Date - 17.11.2023
Exercise 2 - Calling API's
Matriculation No: 1459915
 */