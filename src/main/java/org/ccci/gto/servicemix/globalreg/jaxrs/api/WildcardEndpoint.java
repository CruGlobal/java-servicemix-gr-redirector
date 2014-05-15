package org.ccci.gto.servicemix.globalreg.jaxrs.api;

import static org.ccci.gto.servicemix.globalreg.Constants.PATH_RESOURCE;
import static org.ccci.gto.servicemix.globalreg.Constants.PATH_TYPE;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(PATH_TYPE)
public class WildcardEndpoint {
    @GET
    public Response getAll(@Context final UriInfo uri) {
        return null;
    }

    @POST
    public Response postAll(final String data) {
        return null;
    }

    @PUT
    public Response putAll(final String data) {
        return null;
    }

    @DELETE
    public Response deleteAll(final String data) {
        return null;
    }

    @GET
    @Path(PATH_RESOURCE)
    public Response getSingle(@Context final UriInfo uri) {
        return null;
    }

    @POST
    @Path(PATH_RESOURCE)
    public Response postSingle(final String data) {
        return null;
    }

    @PUT
    @Path(PATH_RESOURCE)
    public Response putSingle(final String data) {
        return null;
    }

    @DELETE
    @Path(PATH_RESOURCE)
    public Response deleteSingle(final String data) {
        return null;
    }
}
