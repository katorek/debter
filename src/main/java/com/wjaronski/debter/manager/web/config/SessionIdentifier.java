package com.wjaronski.debter.manager.web.config;

/*
@Configuration
public class SessionIdentifier extends SocialConfigurerAdapter {

    @Override
    public UserIdSource getUserIdSource() {
        return new SessionIdUserIdSource();
    }

    private static final class SessionIdUserIdSource implements UserIdSource {
        @Override
        public String getUserId() {
            RequestAttributes request = RequestContextHolder.currentRequestAttributes();
            String uuid = (String) request.getAttribute("_socialUserUUID", RequestAttributes.SCOPE_SESSION);
            if (uuid == null) {
                uuid = UUID.randomUUID().toString();
                request.setAttribute("_socialUserUUID", uuid, RequestAttributes.SCOPE_SESSION);
            }
            return uuid;
        }
    }
}*/
