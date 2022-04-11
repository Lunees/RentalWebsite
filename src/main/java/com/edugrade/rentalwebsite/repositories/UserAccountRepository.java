package com.edugrade.rentalwebsite.repositories;

import com.edugrade.rentalwebsite.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,String> {
    UserAccount findByUsername(String username);


}
