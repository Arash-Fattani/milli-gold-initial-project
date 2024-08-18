package com.example.millieprojects.initialproject.financialtransactionsystem.repository;

import com.example.millieprojects.initialproject.financialtransactionsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
