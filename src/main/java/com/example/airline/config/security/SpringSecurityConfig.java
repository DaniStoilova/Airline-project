package com.example.airline.config.security;
import com.example.airline.model.enums.RoleEnum;
import com.example.airline.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig  {

    private final UserRepository userRepository;

    public SpringSecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer
                         .disable())
                 .authorizeHttpRequests(
                         (authorizeRequest)->
                authorizeRequest
                     .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                     .requestMatchers( "/*.css").permitAll()
                     .requestMatchers( "/image/**","/static/**", "/css/**").permitAll()
                     .requestMatchers("/","/about" ,"/users/login-error","/contact","/all","rentAll","/allReservation","/info","addNews").permitAll()
                     .requestMatchers("/users/login", "/users/register").permitAll()
                     .requestMatchers("/add","/car","/hotel").hasRole(RoleEnum.ADMIN.name())
                     .anyRequest().authenticated())
                 .formLogin(
                         (formLogin) ->
                                 formLogin.
                                         loginPage("/users/login").
                                         usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                                         passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                                         defaultSuccessUrl("/",true).
                                         failureForwardUrl("/users/login-error")

                 )
                 .logout((logout) ->
                         logout.logoutUrl("/users/logout").
                                 logoutSuccessUrl("/").
                                 invalidateHttpSession(true).
                                 deleteCookies("JSESSIONID")

                 );

         httpSecurity.sessionManagement()
                 .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        return httpSecurity.build();

    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new AirlineUserDetailService(userRepository);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }
}
