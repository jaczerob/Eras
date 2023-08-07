package dev.jaczerob.madamchuckle.launcher.login.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SuccessfulLoginResponse {
    private String gameserver;
    private String cookie;
}
