package org.ccci.gto.servicemix.globalreg.camel;

import static org.ccci.gto.servicemix.globalreg.Constants.PARAM_TYPE;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

import javax.ws.rs.core.MultivaluedMap;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CxfRequestTypePredicate implements Predicate {
    private Set<String> types = new HashSet<>();

    public Collection<String> getTypes() {
        return this.types;
    }

    public void setTypes(final Collection<String> types) {
        this.types = types != null ? Collections.unmodifiableSet(new HashSet<>(types)) : Collections.<String>emptySet();
    }

    @Override
    public boolean matches(final Exchange exchange) {
        final MultivaluedMap<String, String> path = CamelCxfUtils.getPathParameters(exchange);
        return path != null && this.types.contains(path.getFirst(PARAM_TYPE));
    }
}
