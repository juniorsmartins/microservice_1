package io.pessoas_java.config.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;

//    @Bean
//    PasswordEncoder passwordEncoder() {
//
//        Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000,
//                SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
//
//        Map<String, PasswordEncoder> encoders = new HashMap<>();
//        encoders.put("pbkdf2", pbkdf2Encoder);
//
//        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
//        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
//
//        return passwordEncoder;
//    }

//    @Bean
//    AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    // Filtra as requisições antes de serem recebidas no Controller
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(
                authorizeConfig -> {
                    authorizeConfig.requestMatchers("/logout").permitAll();
                    authorizeConfig.requestMatchers("/api/v1/pessoas/{chave}").permitAll();
                    authorizeConfig.requestMatchers("/swagger-ui/**").permitAll();
                    authorizeConfig.requestMatchers("/v3/api-docs/**").permitAll();
                    authorizeConfig.anyRequest().authenticated();
                })
            .formLogin(Customizer.withDefaults())
            .build();
    }
//        return http
//            .httpBasic().disable()
//            .csrf(AbstractHttpConfigurer::disable)
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .authorizeHttpRequests(authorizeHttpRequests ->
//                authorizeHttpRequests.requestMatchers(
//                        "/auth/signin",
//                        "/auth/refresh/**",
//                        "/swagger-ui/**",
//                        "/v3/api-docs/**"
//                ).permitAll()
//                .requestMatchers("/api/**").authenticated()
//                .requestMatchers("/users").denyAll())
//            .cors()
//            .and()
//            .apply(new JwtConfigurer(jwtTokenProvider))
//            .and()
//            .build();
}

