package org.ccci.gto.servicemix.globalreg.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

public final class NotPredicate implements Predicate {
    private final Predicate predicate;

    public NotPredicate(final Predicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean matches(final Exchange exchange) {
        return !this.predicate.matches(exchange);
    }
}
