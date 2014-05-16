package org.ccci.gto.servicemix.globalreg.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.common.message.CxfConstants;

import javax.ws.rs.core.UriInfo;

public class QueryProcessor implements Processor {
    @Override
    public void process(final Exchange exchange) throws Exception {
        final Message message = exchange.getIn();
        final UriInfo uri = CamelCxfUtils.getUriInfo(message);
        message.setHeader(CxfConstants.CAMEL_CXF_RS_QUERY_MAP, uri.getQueryParameters());
    }
}
