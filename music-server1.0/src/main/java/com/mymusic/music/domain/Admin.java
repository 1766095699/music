package com.mymusic.music.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 管理员
 */
@Data
@NoArgsConstructor
public class Admin implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String sign;
    private Integer status;
    private String nickname;
    private String avatar;
    private String codeurl;
    private String phoneNumber;
}
