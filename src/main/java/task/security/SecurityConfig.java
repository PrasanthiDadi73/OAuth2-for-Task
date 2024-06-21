package task.security;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
	    return new InMemoryClientRegistrationRepository(
	        ClientRegistration.withRegistrationId("github")
	            .clientId("4217f7e5ce22a69ee8d0")
	            .clientSecret("e5bb1364c1fc3afc4572581f83e26e3b58e4615f")
	            .redirectUri("https://oauth.pstmn.io/v1/callback")
	            .authorizationUri("https://github.com/login/oauth/authorize")
	            .tokenUri("https://github.com/login/oauth/access_token")
	            .userInfoUri("https://api.github.com/user")
	            .userNameAttributeName("id")
	            .clientName("GitHub")
	            .authorizationGrantType(org.springframework.security.oauth2.core.AuthorizationGrantType.AUTHORIZATION_CODE)
	            .scope("read")
	            .build()
	    );
	}


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/").permitAll();
                auth.anyRequest().authenticated();
            })
            .oauth2Login(withDefaults())
            .formLogin(withDefaults())
            .build();
    }
}
