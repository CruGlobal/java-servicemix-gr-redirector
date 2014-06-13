package org.ccci.gto.servicemix.globalreg.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

public class AndPredicate implements Predicate {
    private final Predicate[] predicates;

    public AndPredicate(final Predicate... predicates) {
        this.predicates = predicates;
    }

    @Override
    public boolean matches(final Exchange exchange) {
        for (final Predicate predicate : predicates) {
            if (!predicate.matches(exchange)) {
                return false;
            }
        }

        return true;
    }
}
