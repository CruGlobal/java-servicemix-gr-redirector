package org.ccci.gto.servicemix.globalreg.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CxfRequestQueryParamsPredicate implements Predicate {
    private Set<String> params = new HashSet<>();

    public Collection<String> getParams() {
        return this.params;
    }

    public void setParams(final Collection<String> params) {
        this.params = params != null ? Collections.unmodifiableSet(new HashSet<>(params)) : Collections
                .<String>emptySet();
    }

    @Override
    public boolean matches(final Exchange exchange) {
        return CamelCxfUtils.hasValidQueryParams(exchange, this.params.toArray(new String[this.params.size()]));
    }
}
