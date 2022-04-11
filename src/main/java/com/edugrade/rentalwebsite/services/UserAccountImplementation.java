package com.edugrade.rentalwebsite.services;

import com.edugrade.rentalwebsite.entities.UserAccount;
import com.edugrade.rentalwebsite.entities.Role;
import com.edugrade.rentalwebsite.repositories.*;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;



@Service @Transactional
public class UserAccountImplementation implements UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;

    public UserAccountImplementation(UserAccountRepository userAccountRepository, RoleRepository roleRepository) {
        this.userAccountRepository = userAccountRepository;
        this.roleRepository = roleRepository;
    }

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserAccountImplementation.class);

    @Override
    public UserAccount saveUser(UserAccount user) {
        log.info("Saving new user {} to the database", user.getUsername());
        return userAccountRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUserAccount(String username, String roleName) {
        log.info("Adding the role {} to user {}", roleName, username);
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        userAccount.getRoles().add(role);

    }

    @Override
    public UserAccount getUser(String username) {
        log.info("Fetching user {}", username);
        return userAccountRepository.findByUsername(username);
    }

    @Override
    public List<UserAccount> getUsers() {
        log.info("Fetching all users");
        return userAccountRepository.findAll();
    }
}
