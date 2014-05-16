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
        if ("GET".equals(CamelCxfUtils.getHttpMethod(message))) {
            final UriInfo uri = CamelCxfUtils.getUriInfo(message);
            final MultivaluedMap<String, String> path = uri.getPathParameters();
            final MultivaluedMap<String, String> query = uri.getQueryParameters();
            final String ownedBy = query.getFirst("filters[owned_by]");
            if ("entities".equals(path.getFirst(PARAM_TYPE)) && (ownedBy == null || "".equals(ownedBy))) {
                return true;
            }
        }

        return false;
    }
}
