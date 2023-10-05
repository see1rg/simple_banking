package com.see1rg.simple_bancking.repository;

import com.see1rg.simple_bancking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
