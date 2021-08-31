package com.tuwindi.conqueror;

import com.tuwindi.conqueror.utils.ProjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
public class ConquerorServiceApplication {

	/*public static void main(String[] args) {
		SpringApplication.run(ConquerorServiceApplication.class, args);
	}*/

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(ConquerorServiceApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorAware() {
		return () -> Optional.ofNullable(ProjectUtils.username());
	}

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
