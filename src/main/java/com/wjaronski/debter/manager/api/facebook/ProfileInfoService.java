package com.wjaronski.debter.manager.api.facebook;

import com.wjaronski.debter.manager.api.facebook.dto.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Wojciech Jaronski
 */

@Slf4j
@Service
public class ProfileInfoService {
    private final FacebookProfileProvider facebookProfileProvider;
    private final FacebookRoleProvider facebookRoleProvider;

    public ProfileInfoService(FacebookProfileProvider facebookProfileProvider, FacebookRoleProvider facebookRoleProvider) {
        this.facebookProfileProvider = facebookProfileProvider;
        this.facebookRoleProvider = facebookRoleProvider;
    }

    public Profile getProfile() {
        Profile profile = facebookProfileProvider.getProfile();
        profile.setRole(facebookRoleProvider.getRole(profile.getId()));
        log.info("{}", profile);
        return profile;
    }

    public String getRole(String userId) {
        return facebookRoleProvider.getRole(userId);
    }

}
