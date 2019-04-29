package pl.rkulikowski.Restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import pl.rkulikowski.Restaurant.service.CustomUserDetailsService;
import pl.rkulikowski.Restaurant.utils.SecurityConst;

@Configuration  // jest to klasa konfiguracyjna, moze posiadac beany zeby potem sie do nich odwolywac , beda one dostpene w calej aplikacji
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService){
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder registry) throws Exception {
        registry.userDetailsService(customUserDetailsService);
    }

    // to jest confgiuracja potrzebna zeby znajdowalo sobie pliki w folderach
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/static/*", "/static/**", "/css/*", "/js/*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {  // w anyMatches dodajemy akcje ktore beda dostepne dla (permitAll-dla wszystkich, ADMIN- dla adminow, USER-dla userow)
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login","/login/form**","/logout","/registrationConfirm*","/","/user/booking").permitAll()
                .antMatchers("/registration").anonymous()
                .antMatchers("/account/panelAdmin/**","/account/panelAdmin").hasRole("ADMIN")
                .antMatchers("/edit","/panelUser").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login/form")
                .loginProcessingUrl("/login")
                .failureUrl("/login/form?error")
                .defaultSuccessUrl("/account/loginPage")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler());
    }


    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
