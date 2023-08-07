package com.verycoolapp.idea.util;

import com.verycoolapp.idea.exception.TokenAttributeProcessingException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Optional;

@Slf4j
@UtilityClass
public class PrincipalUtils {

    private static final String ERROR_MESSAGE = "Failed to retrieve email value from token.";
    private static final String EMAIL_FIELD = "email";

    public static String getPrincipalEmail(final Object principal) {
        log.debug("Trying to get the email for the principal: {}", principal);
        if (principal instanceof OAuth2AuthenticationToken token) {
            return Optional.of(token)
                    .map(OAuth2AuthenticationToken::getPrincipal)
                    .map(OAuth2AuthenticatedPrincipal::getAttributes)
                    .map(map -> map.get(EMAIL_FIELD))
                    .map(Object::toString)
                    .orElseThrow(() -> new TokenAttributeProcessingException(ERROR_MESSAGE));
        }
        throw new TokenAttributeProcessingException(ERROR_MESSAGE);
    }
}
