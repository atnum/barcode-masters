
package com.anup;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 *
 * @author Raichand
 */
@Configuration
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
/**
 * Above Specified initializer class registers the springSecurityFilter with
 * application war. It is equivalent to below code in XML Configuration in
 * web.xml <filter> <filter-name>springSecurityFilterChain</filter-name>
 * <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
 * </filter> <filter-mapping>
 * <filter-name>springSecurityFilterChain</filter-name>
 * <url-pattern>/*</url-pattern> </filter-mapping>
 **/