package org.ccci.gto.servicemix.globalreg.camel;

import static org.ccci.gto.servicemix.globalreg.Constants.PARAM_TYPE;

import org.apache.camel.Exchange;

import javax.ws.rs.core.MultivaluedMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SingleBestRecordResourcePredicate extends CxfMessagePredicate {
    private final Set<String> resources = new HashSet<>();

    public void setResources(final Collection<String> resources) {
        this.resources.clear();
        if (resources != null) {
            this.resources.addAll(resources);
        }
    }

    @Override
    public boolean matches(final Exchange exchange) {
        final MultivaluedMap<String, String> path = this.getPathParameters(exchange);
        final String type = path != null ? path.getFirst(PARAM_TYPE) : null;
        return type != null && this.resources.contains(type);
    }
}
