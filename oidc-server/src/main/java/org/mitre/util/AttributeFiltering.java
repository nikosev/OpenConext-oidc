package org.mitre.util;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.ComponentScan;


/**
 * @author nikosev
 */
@ComponentScan
public final class AttributeFiltering {

    // Define delimiter including escapes
    public static final String DELIMITER = "\\?value=";

    /**
	 * Logger for this class
	 */
    private static final Logger logger = LoggerFactory.getLogger(AttributeFiltering.class);

	public static boolean isParametricScope(String scope, List<String> parametricScopes) {
		Pattern parametricScopePattern = Pattern.compile(".+?(?=" + DELIMITER + ")");
        Matcher matchedValue;
        Set<String> parScopes = new HashSet<String>(parametricScopes);
        
        matchedValue = parametricScopePattern.matcher(scope);
        if (matchedValue.find()) {
            if (parScopes.contains(matchedValue.group(0))) {
                logger.debug("AttributeFiltering: " + scope + " is a parametric scope.");
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean hasParametricScopes(String scope, Set<String> requestedScopes) {
		Pattern parametricScopePattern = Pattern.compile(".+?" + DELIMITER);
        Matcher matchedValue;
        
        for (String reqScope : requestedScopes) {
            matchedValue = parametricScopePattern.matcher(reqScope);
            if (matchedValue.find() && scope.equals(matchedValue.group(0))) {
                return true;
            }
        }
        return false;
    }

    public static Set<String> getGroupParametricScope(String scope, Set<String> requestedScopes) {
        Pattern parametricScopePattern = Pattern.compile(".+?" + DELIMITER);
        Matcher matchedValue;
        Set<String> result = new HashSet<String>();
        
        matchedValue = parametricScopePattern.matcher(scope);
        if (matchedValue.find()) {
            for (String reqScope : requestedScopes) {
                if (reqScope.contains(matchedValue.group(0))) {
                    String scopeParam = reqScope.replace(matchedValue.group(0), "");
                    result.add(scopeParam);
                }
            }
        }

        return result;
    }

    public static Set<String> filterAttributes(String scope, Set<String> attributeValues, Set<String> requestedScopes, List<String> parametricScopes) {
        Set<String> filters = new HashSet<String>();
        // Remove escape character from delimiter before add in the trageted scope
        scope += DELIMITER.replace("\\", "");

        if (isParametricScope(scope, parametricScopes) && hasParametricScopes(scope, requestedScopes)) {
            filters = getGroupParametricScope(scope, requestedScopes);
            return new HashSet<String>(Sets.intersection(filters, attributeValues));
        }
        return attributeValues;
    }
    
}
