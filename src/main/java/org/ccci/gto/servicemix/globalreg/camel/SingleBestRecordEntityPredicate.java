package org.ccci.gto.servicemix.globalreg.camel;

import static org.ccci.gto.servicemix.globalreg.Constants.PARAM_TYPE;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Predicate;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

public class SingleBestRecordEntityPredicate implements Predicate {
    @Override
    public boolean matches(final Exchange exchange) {
        final Message message = exchange.getIn();
        if (message.getHeader(Exchange.HTTP_METHOD).equals("GET")) {
            final UriInfo uri = message.getBody(UriInfo.class);
            final MultivaluedMap<String, String> path = uri.getPathParameters();
            final MultivaluedMap<String, String> query = uri.getQueryParameters();
            final String createdBy = query.getFirst("created_by");
            if ("entities".equals(path.getFirst(PARAM_TYPE)) && (createdBy == null || "".equals(createdBy))) {
                return true;
            }
        }

        return false;
    }
}
