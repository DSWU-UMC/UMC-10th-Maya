package org.example.umc10th.global.security.dto;

import org.example.umc10th.domain.user.enums.SocialType;

public interface OAuthDTO {
    SocialType getSocialType();
    String getSocialUid();
    String getSocialEmail();
    String getName();
}
