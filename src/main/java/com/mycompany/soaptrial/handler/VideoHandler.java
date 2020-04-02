/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soaptrial.handler;

import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author YMP
 */
public class VideoHandler implements SOAPHandler<SOAPMessageContext> {
    
    public boolean handleMessage(SOAPMessageContext messageContext) {
        try {
            boolean b1 = (boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
            if(!b1){
            if(messageContext.getMessage().getSOAPBody().getFirstChild().getLocalName().equals("addVideos")){
            Node msg = (Node) messageContext.getMessage().getSOAPBody().getFirstChild().getFirstChild();
            if(msg.getTextContent().equalsIgnoreCase("disney")){
                return false;
            }
            }
            
            }
        } catch (SOAPException ex) {
            Logger.getLogger(VideoHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }
    
    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }
    
    public void close(MessageContext context) {
    }
    
}
