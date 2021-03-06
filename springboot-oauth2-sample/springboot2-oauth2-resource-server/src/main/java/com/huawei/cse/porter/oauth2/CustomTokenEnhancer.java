package com.huawei.cse.porter.oauth2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {
  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
    final Map<String, Object> additionalInfo = new HashMap<>();
    //��ȡ��¼��Ϣ
    UserDetails user = (UserDetails) oAuth2Authentication.getUserAuthentication().getPrincipal();
    additionalInfo.put("userName", user.getUsername());
    additionalInfo.put("authorities", user.getAuthorities());
    ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
    return oAuth2AccessToken;
  }
}
