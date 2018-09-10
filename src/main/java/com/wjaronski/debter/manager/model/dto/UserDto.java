package com.wjaronski.debter.manager.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wjaronski.debter.manager.model.User;
import lombok.Data;

/**
 * Created by wojta
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
class UserDto {
    private String name;
    private String email;

    UserDto(User user) {
        name = user.getName();
        email = user.getEmail();
    }
}
