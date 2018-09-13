package com.wjaronski.debter.manager.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wjaronski.debter.manager.api.domain.UserBean;
import lombok.Data;

/**
 * Created by wojta
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
class UserDto {
    private String name;
    private String email;

    UserDto(UserBean userBean) {
        name = userBean.getLogin();
        email = userBean.getEmail();
    }
}
