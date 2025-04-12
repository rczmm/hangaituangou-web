package com.dsy.hangaituangou.domain.bo;

import com.dsy.hangaituangou.domain.base.PageBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserProfileBO extends PageBase {

    private String searchKey;

    private String common;

}
