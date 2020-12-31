package com.guodf.owner.entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Table(name = "sys_login_info")
public class SysCmsLoginInfo {
    @Id
    private Integer uid;

    private Long password;

    private String username;

    private String nickname;

    private Integer sex;
}