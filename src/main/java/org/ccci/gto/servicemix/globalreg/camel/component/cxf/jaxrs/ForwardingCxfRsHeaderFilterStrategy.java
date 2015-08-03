package org.ccci.gto.servicemix.globalreg.camel.component.cxf.jaxrs;

import org.apache.camel.component.cxf.jaxrs.CxfRsHeaderFilterStrategy;
import org.apache.cxf.message.Message;

import java.util.Locale;

public class ForwardingCxfRsHeaderFilterStrategy extends CxfRsHeaderFilterStrategy {
    @Override
    protected void initialize() {
        super.initialize();

        // we don't want to strip the Content-Type header from requests
        getOutFilter().remove(Message.CONTENT_TYPE.toLowerCase(Locale.ENGLISH));
    }
}
