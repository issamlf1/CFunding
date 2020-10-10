package com.crowdfunding;


import com.crowdfunding.entities.User;
import com.crowdfunding.metier.ProjetMetier;
import com.crowdfunding.metier.UserMetier;
import com.crowdfunding.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CrowdfundingApplication implements CommandLineRunner {
	@Autowired
	UserMetier userMetier;
	@Autowired
	ProjetMetier projetMetier ;
	public static void main(String[] args) {
		SpringApplication.run(CrowdfundingApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		projetMetier.checkProjects();
		userMetier.saveUser(new User("admin","admin","admin","admin","admin","admin"));
	}
	@Bean(name = "UserDetailsServiceImpl")
	public UserDetailsService getUserDetails(){
		return new UserDetailsServiceImpl(); // Implementation class
	}
}

