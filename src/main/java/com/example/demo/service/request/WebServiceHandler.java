package com.example.demo.service.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.HashSet;
import java.util.Set;

public class WebServiceHandler implements SOAPHandler<SOAPMessageContext> {
    //    private static Logger logger = LoggerContext
    private static final Logger log = LoggerFactory.getLogger(WebServiceHandler.class);

    private String key;
    private String value;

    public WebServiceHandler(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Set<QName> getHeaders() {
        return new HashSet<>();
    }

    @Override
    public boolean handleMessage(SOAPMessageContext messageContext) {
        SOAPMessage msg = messageContext.getMessage();
        if ((Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
            try {
                SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
                if (envelope.getHeader() != null) {
                    envelope.getHeader().detachNode();
                }
                SOAPHeader header = envelope.addHeader();
                SOAPElement el = header.addChildElement(envelope.createName(key, envelope.getPrefix(), envelope.getNamespaceURI()));
                el.setValue(value);
                msg.saveChanges();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
