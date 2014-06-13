package org.ccci.gto.servicemix.globalreg.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CxfRequestMethodPredicate implements Predicate {
    private Set<String> methods = new HashSet<>();

    public Collection<String> getMethods() {
        return this.methods;
    }

    public void setMethods(final Collection<String> methods) {
        this.methods = methods != null ? Collections.unmodifiableSet(new HashSet<>(methods)) : Collections
                .<String>emptySet();
    }

    @Override
    public boolean matches(final Exchange exchange) {
        final String method = CamelCxfUtils.getHttpMethod(exchange);
        return this.methods.contains(method);
    }
}
