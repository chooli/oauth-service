package com.jumkid.oauthservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final TokenStore tokenStore;

    private final BCryptPasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthorizationServerConfig(AuthenticationManager authenticationManager, BCryptPasswordEncoder passwordEncoder,
                                     TokenStore tokenStore) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenStore = tokenStore;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.tokenKeyAccess("permitAll()")
                        .checkTokenAccess("permitAll()")
                        .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
          .withClient("jumkid")
          .secret(passwordEncoder.encode("secret"))
          .authorizedGrantTypes("password", "client_credentials", "implicit", "refresh_token")
          .authorities("CLIENT")
          .scopes("read", "write")
          .autoApprove(true)
          .redirectUris("/oauth/token")
          .accessTokenValiditySeconds(120)
          .refreshTokenValiditySeconds(240000); 
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager);
    }

}