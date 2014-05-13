package org.ccci.gto.servicemix.globalreg.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.cxf.message.Message;

import javax.ws.rs.core.MultivaluedMap;

public abstract class CxfMessagePredicate implements Predicate {
    protected String getHttpMethod(final org.apache.camel.Message message) {
        if (message != null) {
            return message.getHeader(Exchange.HTTP_METHOD, String.class);
        }

        return null;
    }

    protected String getHttpMethod(final Exchange exchange) {
        return this.getHttpMethod(exchange.getIn());
    }

    protected Message getCxfMessage(final Exchange exchange) {
        return exchange.getIn().getHeader("CamelCxfMessage", Message.class);
    }

    protected MultivaluedMap<String, String> getPathParameters(final Exchange exchange) {
        return this.getPathParameters(this.getCxfMessage(exchange));
    }

    protected MultivaluedMap<String, String> getPathParameters(final Message message) {
        if (message != null) {
            final Object path = message.get("jaxrs.template.parameters");
            if (path instanceof MultivaluedMap) {
                return (MultivaluedMap<String, String>) path;
            }
        }

        return null;
    }
}
