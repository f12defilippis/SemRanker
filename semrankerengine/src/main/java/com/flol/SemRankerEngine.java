package com.flol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan("com.flol.semrankerengine")
@Configuration
@EnableJpaRepositories(basePackages = {"com.flol.semrankercommon.repository"})
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableGlobalMethodSecurity
public class SemRankerEngine {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SemRankerEngine.class);
        app.setShowBanner(false);
        app.run(args);
    }	
}

//@Configuration
//class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
//
////	@Autowired
////	AccountRepository accountRepository;
//
//	@Override
//	public void init(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService());
//	}
//
//	@Bean
//	UserDetailsService userDetailsService() {
//		return new UserDetailsService() {
//
//			@Override
//			public UserDetails loadUserByUsername(String username)
//					throws UsernameNotFoundException {
//				return null;
////				Account account = accountRepository.findByUsername(username);
////				if (account != null) {
////					return new User(account.getUsername(),
////							account.getPassword(), true, true, true, true,
////							AuthorityUtils.createAuthorityList("USER"));
////				} else {
////					throw new UsernameNotFoundException(
////							"could not find the user '" + username + "'");
////				}
//			}
//
//		};
//	}
//}
//
//@EnableWebSecurity
//@Configuration
//class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.csrf().disable();
//  }
//}
