package edugrade.rentalwebsite.controllers;


import edugrade.rentalwebsite.entities.Role;
import edugrade.rentalwebsite.entities.UserAccount;
import edugrade.rentalwebsite.services.UserAccountService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/api")
@RestController
public class UserAccountController {
    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }
        @GetMapping("/user-Account")
        public ResponseEntity<List<UserAccount>>getUsers(){
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user-Account/save").toUriString());
            return ResponseEntity.created(uri).body(userAccountService.getUsers());
        }

        @PostMapping("/role/save")
        public ResponseEntity<Role>saveRole(@RequestBody Role role) {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
            return ResponseEntity.created(uri).body(userAccountService.saveRole(role));
        }

        @PostMapping("/role/addRoletoAccount")
        public ResponseEntity<?>addRoleToAccount(@RequestBody @NotNull RoleToUserForm form) {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
            userAccountService.addRoleToUserAccount(form.getUserAccountName(),form.getRoleName());
            return ResponseEntity.ok().build();

    }

    static class RoleToUserForm {
        private String userAccountName;
        private String roleName;

        public String getUserAccountName() {
            return userAccountName;
        }

        public void setUserAccountName(String userAccountName) {
            this.userAccountName = userAccountName;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }
}
