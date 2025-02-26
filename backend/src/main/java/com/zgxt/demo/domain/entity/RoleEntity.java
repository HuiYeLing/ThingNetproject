package com.zgxt.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.zgxt.demo.common.Constants.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {
    int user;
    String address;
    String roleModel;

    public RoleEntity init() {
        switch (this.user) {
            case 0:
                this.roleModel = PRODUCE_ROLE;
                break;
            case 1:
                this.roleModel = DISTRIBUTION_ROLE;
                break;
            case 2:
                this.roleModel = RETAIL_ROLE;
                break;
            default:
                break;
        }
        return this;

    }
}
