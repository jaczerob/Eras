package com.github.jaczerob.madamchuckle.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.github.jaczerob.madamchuckle.configurations.ToontownClientConfig;
import com.github.jaczerob.madamchuckle.toontown.models.login.Authentication;
import com.github.jaczerob.madamchuckle.toontown.models.login.LoginInfo;
import com.github.jaczerob.madamchuckle.toontown.models.login.LoginResponse;
import com.github.jaczerob.madamchuckle.toontown.models.login.QueueToken;

import feign.Headers;

@FeignClient(
    name="toontownClient", 
    url="https://www.toontownrewritten.com/api",
    configuration=ToontownClientConfig.class
)
public interface ToontownClient {
    @PostMapping(value="/login?format=json")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public LoginResponse login(LoginInfo loginInfo);
    
    @PostMapping(value="/login?format=json")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public LoginResponse updateQueue(QueueToken queueToken);

    @PostMapping(value="/login?format=json")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public LoginResponse authenticate(Authentication authentication);
}
