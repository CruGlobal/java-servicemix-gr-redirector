package org.ccci.gto.servicemix.globalreg.camel;

import static org.ccci.gto.servicemix.globalreg.Constants.PARAM_TYPE;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Predicate;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Collection;

public class SingleBestRecordEntityPredicate implements Predicate {
    private static final com.google.common.base.Predicate<String> PREDICATE_NOT_EMPTY = Predicates.and(Predicates
            .notNull(), Predicates.not(Predicates.containsPattern("^\\s*$")));

    @Override
    public boolean matches(final Exchange exchange) {
        final Message message = exchange.getIn();
        if ("GET".equals(CamelCxfUtils.getHttpMethod(message))) {
            final UriInfo uri = CamelCxfUtils.getUriInfo(message);
            final MultivaluedMap<String, String> path = uri.getPathParameters();
            final MultivaluedMap<String, String> query = uri.getQueryParameters();

            // entities endpoint without owned_by filters, send it to SBR
            return "entities".equals(path.getFirst(PARAM_TYPE)) && !this.hasOwnedByFilters(query);
        }

        return false;
    }

    boolean hasOwnedByFilters(final MultivaluedMap<String, String> query) {
        // check for all possible owned_by filters, filtering out blank filters
        final Collection<String> ownedBy = new ArrayList<>();
        for (final String param : new String[]{"filters[owned_by]", "filters[owned_by][]"}) {
            final Collection<String> values = query.get(param);
            if (values != null) {
                ownedBy.addAll(Collections2.filter(values, PREDICATE_NOT_EMPTY));
            }
        }
        return !ownedBy.isEmpty();
    }
}
