package org.ccci.gto.servicemix.globalreg.camel;

import static org.ccci.gto.servicemix.globalreg.Constants.PARAM_TYPE;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Predicate;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SingleBestRecordResourcePredicate implements Predicate {
    private final Set<String> resources = new HashSet<>();

    public void setResources(final Collection<String> resources) {
        this.resources.clear();
        if (resources != null) {
            this.resources.addAll(resources);
//            for (final String resource : resources) {
//                this.resources.add(resource != null ? resource.toLowerCase(Locale.US) : null);
//            }
        }
    }

    @Override
    public boolean matches(final Exchange exchange) {
        final Message message = exchange.getIn();
        final UriInfo uri = message.getBody(UriInfo.class);
        final MultivaluedMap<String, String> path = uri.getPathParameters();
        final String type = path.getFirst(PARAM_TYPE);
        return type != null && this.resources.contains(type);
    }
}
