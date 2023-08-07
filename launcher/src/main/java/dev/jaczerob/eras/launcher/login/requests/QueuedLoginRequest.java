package dev.jaczerob.eras.launcher.login.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QueuedLoginRequest implements LoginRequest {
    private String queueToken;
}
