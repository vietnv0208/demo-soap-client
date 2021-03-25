package com.example.demo.service.request;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;
import java.util.TreeSet;

public class AuthorHeaderHandler implements SOAPHandler<SOAPMessageContext> {

    private String tocken;
    private String username;
    private String password;

    public AuthorHeaderHandler(String tocken, String username, String password) {
        this.tocken = tocken;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext smc) {

        Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (outboundProperty.booleanValue()) {
            SOAPMessage message = smc.getMessage();
            try {
                SOAPEnvelope envelope = smc.getMessage().getSOAPPart().getEnvelope();
                if (envelope.getHeader() != null) {
                    envelope.getHeader().detachNode();
                }
                SOAPHeader header = envelope.addHeader();
                SOAPElement security =
                        header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");

                SOAPElement usernameToken =
                        security.addChildElement("UsernameToken", "wsse");
                usernameToken.addAttribute(new QName("xmlns:wsu"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
                usernameToken.addAttribute(new QName("wsu:Id"), tocken);

                SOAPElement usernameElement =
                        usernameToken.addChildElement("Username", "wsse");
                usernameElement.addTextNode(username);

                SOAPElement passwordElement =
                        usernameToken.addChildElement("Password", "wsse");
                passwordElement.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
                passwordElement.addTextNode(password);

                //Print out the outbound SOAP message to System.out
                message.saveChanges();
                message.writeTo(System.out);
                System.out.println("");

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {

                //This handler does nothing with the response from the Web Service so
                //we just print out the SOAP message.
                SOAPMessage message = smc.getMessage();
                message.writeTo(System.out);
                System.out.println("");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


        return outboundProperty;

    }


    public Set<QName> getHeaders() {
        return new TreeSet();
    }

    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    public void close(MessageContext context) {
        //
    }
}
