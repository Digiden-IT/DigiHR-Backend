package dev.jahid.user_auth_db_config_boilerplate.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    String accessToken;
    String refreshToken;
}
