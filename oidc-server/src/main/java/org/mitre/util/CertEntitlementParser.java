package org.mitre.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.ComponentScan;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author nikosev
 */
@ComponentScan
public final class CertEntitlementParser {

    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(CertEntitlementParser.class);

    public static JsonArray buildCertEntitlementClaimIntrospect(Set<String> certEntitlementSet) {
        JsonParser parser = new JsonParser();
        JsonArray certEntitlementArray = new JsonArray();
        JsonObject certEntitlement;
        
        for (String certEntitlementString : certEntitlementSet) {
            logger.debug("CertEntitlementParser: buildCertEntitlementClaimIntrospect: INPUT SET= " + certEntitlementString);
            JsonObject json = parser.parse(certEntitlementString).getAsJsonObject();
            certEntitlement = new JsonObject();
            certEntitlement.addProperty("cert_subject_dn", json.get("cert_subject_dn").getAsString());
            certEntitlement.addProperty("cert_iss", json.get("cert_iss").getAsString());
            certEntitlement.addProperty("eduperson_entitlement", json.get("eduperson_entitlement").getAsString());

            logger.debug("CertEntitlementParser: buildCertEntitlementClaimIntrospect: OUTPUT ITEM= " + certEntitlement.toString());
            certEntitlementArray.add(certEntitlement);
        }

        return certEntitlementArray;
    }

    public static JsonArray buildCertEntitlementClaimUserInfo(JsonElement certEntitlementElement) {
        logger.debug("CertEntitlementParser: buildCertEntitlementClaimUserInfo: INPUT= " + certEntitlementElement.toString());
        JsonParser parser = new JsonParser();
        JsonArray result = new JsonArray();
        JsonObject certEntitlement;
        JsonArray certEntitlementArray = certEntitlementElement.getAsJsonArray();

        for (JsonElement certEntitlementItem : certEntitlementArray) {
            String jsonString = certEntitlementItem.getAsString();
            JsonObject json = parser.parse(jsonString).getAsJsonObject();

            certEntitlement = new JsonObject();
            certEntitlement.addProperty("cert_subject_dn", json.get("cert_subject_dn").getAsString());
            certEntitlement.addProperty("cert_iss", json.get("cert_iss").getAsString());
            certEntitlement.addProperty("eduperson_entitlement", json.get("eduperson_entitlement").getAsString());

            logger.debug("CertEntitlementParser: buildCertEntitlementClaimUserInfo: OUTPUT ITEM= " + certEntitlement.toString());
            result.add(certEntitlement);
        }

        return result;
    }

    public static Set<String> listToSet(List<String> jsonListString) {
        String jsonString = jsonListString.get(0);
        JsonParser parser = new JsonParser();
        Set<String> certEntitlementSet = new HashSet<>();

        JsonObject rootObj = parser.parse(jsonString).getAsJsonObject();
        JsonArray certEntitlementArray = rootObj.getAsJsonArray("cert_entitlement");

        for (JsonElement certEntitlementObj : certEntitlementArray) {
            JsonObject certEntitlement = certEntitlementObj.getAsJsonObject();
            logger.debug("CertEntitlementParser: listToSet: OUTPUT SET= " + certEntitlement.toString());
            certEntitlementSet.add(certEntitlement.toString());
        }
        return certEntitlementSet;
    }

}
