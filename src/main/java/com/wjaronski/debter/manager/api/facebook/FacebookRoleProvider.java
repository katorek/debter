package com.wjaronski.debter.manager.api.facebook;
/*

import com.wjaronski.debter.manager.api.facebook.dto.FacebookAppToken;
import com.wjaronski.debter.manager.api.facebook.dto.FacebookRole;
import com.wjaronski.debter.manager.api.facebook.dto.FacebookRolesData;
import com.wjaronski.debter.manager.api.facebook.dto.UserRole;
import com.wjaronski.debter.manager.api.facebook.properties.FacebookProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

*/
/**
 * Created by Wojciech Jaronski
 *//*


@Slf4j
@Service
@ApplicationScope
public class FacebookRoleProvider {
    private static final String GRAPH_API_BASE_URL = "https://graph.facebook.com/v3.1/";

    private final RestTemplate restTemplate;
    private final FacebookProperties facebookProperties;

    public FacebookRoleProvider(FacebookProperties facebookProperties) {
        this.facebookProperties = facebookProperties;
        restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(getBearerTokenInterceptor(getAccessToken()));
        List<FacebookRole> roles = getRole();
        roles.forEach(it -> log.info("{}", it.toString()));
    }

    private String getAccessToken() {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("graph.facebook.com")
                .path("/oauth/access_token")
                .queryParam("client_id", facebookProperties.getClientId())
                .queryParam("client_secret", facebookProperties.getClientSecret())
                .queryParam("redirect_uri", facebookProperties.getRedirectHost())
                .queryParam("grant_type", "client_credentials")
                .build();

        FacebookAppToken token = new RestTemplate().getForObject(uri.toUri(), FacebookAppToken.class);

        return Objects.requireNonNull(token).getAccess_token();
    }

    private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken) {
        return (request, bytes, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + accessToken);
            return execution.execute(request, bytes);
        };
    }

    public UserRole getRole(String id) {
        return getRole().stream().filter(role -> role.getUser().equals(id)).findFirst().map(it -> UserRole.getFromFacebookRole(it.getRole())).orElse(UserRole.USER);
    }

    public UserRole getRole(Long id) {
        return getRole(id.toString());
    }

    private List<FacebookRole> getRole() {
        FacebookRolesData roles = restTemplate.getForObject(
                GRAPH_API_BASE_URL + facebookProperties.getClientId() + "/roles",
                FacebookRolesData.class);

        return Objects.requireNonNull(roles).getData();
    }
}
*/
