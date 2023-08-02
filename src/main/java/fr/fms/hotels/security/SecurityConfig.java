package fr.fms.hotels.security;
import fr.fms.hotels.security.entities.AppUser;
import fr.fms.hotels.security.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.ArrayList;
import java.util.Collection;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };
    }
    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }
    @Autowired
    private AccountServiceImpl accountService;
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests().antMatchers(HttpMethod.GET,"/hotels/**","/cities/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/login/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/photo/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/photo/**").hasAuthority("MANAGER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/photo/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/hotels/**","/cities/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/hotels/**","/cities/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/hotels/**","/cities/**").hasAnyAuthority("ADMIN","MANAGER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/users").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/users").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/role").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/roles").hasAuthority("ADMIN");
       // http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/roleUser").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                AppUser user = accountService.findUserByUsername(username);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                user.getRoles().forEach( role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getRolename()));
                });
                return new User(user.getUsername(), user.getPassword(), authorities);
            }
        }));}
}
