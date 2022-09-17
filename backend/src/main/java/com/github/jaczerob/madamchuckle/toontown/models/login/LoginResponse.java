package com.github.jaczerob.madamchuckle.toontown.models.login;

import lombok.Data;

@Data
public class LoginResponse {
    private String success;
    private String banner;
    private String responseToken;
    private String gameserver;
    private String cookie;
    private String eta;
    private String position;
    private String queueToken;

    public void update(LoginResponse updatedLoginResponse) {
        this.setSuccess(updatedLoginResponse.getSuccess());
        this.setBanner(updatedLoginResponse.getBanner());
        this.setResponseToken(updatedLoginResponse.getResponseToken());
        this.setGameserver(updatedLoginResponse.getGameserver());
        this.setCookie(updatedLoginResponse.getCookie());
        this.setEta(updatedLoginResponse.getEta());
        this.setPosition(updatedLoginResponse.getPosition());
        this.setQueueToken(updatedLoginResponse.getQueueToken());
    }
}
