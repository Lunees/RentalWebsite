package edugrade.rentalwebsite.Security;

import edugrade.rentalwebsite.services.UserAccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserAccountDetailsService userAccountDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userAccountDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Autowired
    public SecurityConfig(UserAccountDetailsService userAccountDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userAccountDetailsService = userAccountDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
        }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/cars","/register").permitAll()
                .antMatchers("/orders/customer-id","/bookingform","/save-booking","/update/booking-id","/delete-booking").hasAnyRole("ADMIN","CUSTOMER")
                .antMatchers("/admin","/admin/customers","/admin/customers/customer-id","/delete-customer","/admin/vehicles","/admin/add-vehicle","/save-vehicle","/admin/vehicle/vehicle-id","/delete-vehicle" ).hasRole("ADMIN")
                .and().formLogin()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
    }
}

