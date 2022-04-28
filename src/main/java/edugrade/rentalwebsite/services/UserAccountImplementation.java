package edugrade.rentalwebsite.services;

import edugrade.rentalwebsite.entities.UserAccount;
import edugrade.rentalwebsite.entities.Role;
import edugrade.rentalwebsite.repositories.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



@Service @Transactional
public class UserAccountImplementation implements UserAccountService, UserDetailsService {
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;

    public UserAccountImplementation(UserAccountRepository userAccountRepository, RoleRepository roleRepository) {
        this.userAccountRepository = userAccountRepository;
        this.roleRepository = roleRepository;
    }

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserAccountImplementation.class);


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        if (userAccount == null) {
            log.error("Username not found");
            throw new UsernameNotFoundException("Username could not be found");
        } else {
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userAccount.getRoles().forEach(role ->  {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(userAccount.getUsername(), userAccount.getPassword(),authorities);
        }

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
