package com.agency.provider;

import java.io.IOException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

@Provider
public class CorsFilter implements ContainerResponseFilter {
 
	@Override
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response)
	{

		response.getHttpHeaders().putSingle("Access-Control-Allow-Origin", "*");
		response.getHttpHeaders().putSingle("Access-Control-Allow-Credentials", "true");
		response.getHttpHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
		response.getHttpHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
		
		return response;

	}
}
