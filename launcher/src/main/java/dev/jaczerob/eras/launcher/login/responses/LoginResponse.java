package dev.jaczerob.eras.launcher.login.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {
    private String success;
    private String banner;
    private String responseToken;
    private String gameserver;
    private String cookie;
    private String eta;
    private String position;
    private String queueToken;
}
