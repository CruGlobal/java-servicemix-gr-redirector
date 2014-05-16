package org.ccci.gto.servicemix.globalreg.camel;

import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Message;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

public final class CamelCxfUtils {
    private CamelCxfUtils() {
    }

    public static String getHttpMethod(final Exchange exchange) {
        return exchange != null ? getHttpMethod(exchange.getIn()) : null;
    }

    public static String getHttpMethod(final org.apache.camel.Message message) {
        return message != null ? message.getHeader(Exchange.HTTP_METHOD, String.class) : null;
    }

    public static Message getCxfMessage(final Exchange exchange) {
        return exchange != null ? getCxfMessage(exchange.getIn()) : null;
    }

    public static Message getCxfMessage(final org.apache.camel.Message message) {
        return message != null ? message.getHeader(CxfConstants.CAMEL_CXF_MESSAGE, Message.class) : null;
    }

    public static UriInfo getUriInfo(final Exchange exchange) {
        return exchange != null ? getUriInfo(exchange.getIn()) : null;
    }

    public static UriInfo getUriInfo(final org.apache.camel.Message message) {
        return getUriInfo(getCxfMessage(message));
    }

    public static UriInfo getUriInfo(final Message message) {
        return message != null ? JAXRSUtils.createContextValue(message, UriInfo.class, UriInfo.class) : null;
    }

    public static MultivaluedMap<String, String> getPathParameters(final Exchange exchange) {
        return getPathParameters(getCxfMessage(exchange));
    }

    public static MultivaluedMap<String, String> getPathParameters(final Message message) {
        return message != null ? getUriInfo(message).getPathParameters() : null;
    }
}
