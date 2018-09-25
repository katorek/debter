package com.wjaronski.debter.manager.web.controller.dto;

import com.wjaronski.debter.manager.api.domain.UserBean;
import lombok.Data;

/**
 * Created by Wojciech Jaronski
 */

@Data
public class UserDto {
    private Long id;
    private String name;

    public UserDto(UserBean user) {
        id = user.getId();
        name = user.getName();
    }
}
