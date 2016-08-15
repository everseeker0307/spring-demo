package com.everseeker.config;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

/**
 * Created by everseeker on 16/8/8.
 */
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        packages("com.everseeker.action");
        register(JacksonFeature.class);
        register(JacksonJsonProvider.class);
        register(CORSFilter.class);
        property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
    }

    /**
     * CORSFilter: 增加对跨域访问的支持
     */
    static class CORSFilter implements ContainerResponseFilter {

        public void filter(ContainerRequestContext creq, ContainerResponseContext cresp) throws IOException {
            cresp.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
            cresp.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
            cresp.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
            cresp.getHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
        }
    }
}

