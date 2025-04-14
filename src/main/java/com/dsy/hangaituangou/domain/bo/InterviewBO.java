package com.dsy.hangaituangou.domain.bo;

import com.dsy.hangaituangou.domain.base.PageBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InterviewBO extends PageBase {

    private String jobName;

}
