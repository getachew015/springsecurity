package com.dagim.auth;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class AppConfiguration {
	
	/*
	 *	to test the application 
		# Create test users e.g a json array of users included in the /auth/src/main/resources/datasql file
		# use postman to send a POST request to https://localhost:8443/auth-service/user 
		# make sure to use https instead of http since the server is using a self signed certificate
		# Then try to login using the test users provided in the previous steps for the form https://localhost:8443/login
 
	 */
	
	/*
	 * To Generate self-signed certificate using java -keytool.exe run it as admin from cmd in your jdk path
	 * $JAVA_HOME/bin and then run the below command
	 * keytool -genkey -alias mysslCert -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore mysslCert.p12 -validity 3650
	 * keytool -genkey -alias selfsigned_eshop_sslserver -keyalg RSA -keysize 2048 -validity 700 -keypass 350psslcert -storepass 350psslcert -keystore eshop-ssl-server.p12
	 * The embeded servlet container needs to be configured to user SSL and 
	 * also be configured to reroute all incoming http trafic to use https on the new port
	 */


	
    @Bean
    public ServletWebServerFactory servletContainer() {

    	TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
    		
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
            
        };

        tomcat.addAdditionalTomcatConnectors(getHttpConnector());

        return tomcat;
    }


    
    private Connector getHttpConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }

	
	
}

