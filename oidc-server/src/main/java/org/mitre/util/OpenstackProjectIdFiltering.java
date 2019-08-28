package org.mitre.util;

import com.google.common.collect.Sets;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import oidc.model.DefaultOpenstackProjectId;

public class OpenstackProjectIdFiltering {
    
    public static Set<DefaultOpenstackProjectId> createDynamincClaims(Set<String> userEntitlements, Set<String> requestedScopes, List<String> parametricScopes, String userSub){
        Set<DefaultOpenstackProjectId> openstackProjectIds = new HashSet<DefaultOpenstackProjectId>();
        for (String voEntitlement : getScopes("https://aai.egi.eu/openstack_project_id", userEntitlements, requestedScopes, parametricScopes)) {
			if (userEntitlements.contains(voEntitlement)) {
                DefaultOpenstackProjectId projectId = new DefaultOpenstackProjectId();
				projectId.setOpenstackProjectId(getSHA(userSub + voEntitlement));
				projectId.setScopeName("https://aai.egi.eu/openstack_project_id" + AttributeFiltering.DELIMITER.replace("\\", "") + voEntitlement);
				openstackProjectIds.add(projectId);
			}
        }
        return openstackProjectIds;
    }

    public static Set<String> getScopes(String scope, Set<String> attributeValues, Set<String> requestedScopes, List<String> parametricScopes) {
        Set<String> filters = new HashSet<String>();
        // Remove escape character from delimiter before add in the trageted scope
        scope += AttributeFiltering.DELIMITER.replace("\\", "");

        if (AttributeFiltering.isParametricScope(scope, parametricScopes) && AttributeFiltering.hasParametricScopes(scope, requestedScopes)) {
            filters = AttributeFiltering.getGroupParametricScope(scope, requestedScopes);
            return new HashSet<String>(Sets.intersection(filters, attributeValues));
        }
        return new HashSet<String>();
    }

    public static String getSHA(String input) {

        try {

            // Static getInstance method is called with hashing SHA
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // digest() method called
            // to calculate message digest of an input
            // and return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown" + " for incorrect algorithm: " + e);
            return null;
        }
    }

}