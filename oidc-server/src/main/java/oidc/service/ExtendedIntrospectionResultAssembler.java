package oidc.service;

import oidc.model.FederatedUserInfo;
import org.mitre.oauth2.model.OAuth2AccessTokenEntity;
import org.mitre.oauth2.service.impl.DefaultIntrospectionResultAssembler;
import org.mitre.openid.connect.model.UserInfo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.mitre.openid.connect.config.ConfigurationPropertiesBean;
import org.mitre.util.AttributeFiltering;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;

@Service
@Primary
public class ExtendedIntrospectionResultAssembler extends DefaultIntrospectionResultAssembler {
    
    @Autowired
    private ConfigurationPropertiesBean config;
  
    @Override
    public Map<String, Object> assembleFrom(OAuth2AccessTokenEntity accessToken, UserInfo userInfo, Set<String>
        authScopes) {
        Map<String, Object> result = super.assembleFrom(accessToken, userInfo, authScopes);
        Set<String> scopes = new HashSet<String>(Sets.intersection(authScopes, accessToken.getScope()));
        for (String scope : accessToken.getScope()) {
            if (AttributeFiltering.isParametricScope(scope, config.getParametricScopes())) {
                scopes.add(scope);
            }
        }
        result.put(SCOPE, Joiner.on(SCOPE_SEPARATOR).join(scopes));

        if (userInfo != null && userInfo instanceof FederatedUserInfo) {
            FederatedUserInfo federatedUserInfo = (FederatedUserInfo) userInfo;
            Set<String> originalAttributes = new HashSet<String>(federatedUserInfo.getEduPersonEntitlements());
            Set<String> filteredAttributes = new HashSet<String>();
            filteredAttributes = AttributeFiltering.filterAttributes("eduperson_entitlement", originalAttributes, scopes, config.getParametricScopes());
            result.put("iss", config.getIssuer());
            result.put("authenticating_authority", federatedUserInfo.getAuthenticatingAuthority());
            result.put("acr", federatedUserInfo.getAcr());
            result.put("eduperson_assurance", federatedUserInfo.getEduPersonAssurance());
            if (config.isClaimEduPersonEntitlementOld()) {
                result.put("edu_person_entitlements", filteredAttributes);
            }
            if (config.isClaimEduPersonEntitlement()) {
                result.put("eduperson_entitlement", filteredAttributes);
            }
            if (config.isClaimEduPersonScopedAffiliationOld()) {
                result.put("edu_person_scoped_affiliations", federatedUserInfo.getEduPersonScopedAffiliations());
            }
            if (config.isClaimEduPersonScopedAffiliation()) {
                result.put("eduperson_scoped_affiliation", federatedUserInfo.getEduPersonScopedAffiliations());
            }
        }
        return result;
    }

}
