package com.example.demo.service;

import com.example.demo.service.request.AuthorHeaderHandler;
import com.viettel.bccs.boc2.service.*;
import org.springframework.stereotype.Service;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.List;

@Service
public class GbocServiceImpl {

    private static Boc2CrmAppService port;

    private Boc2CrmAppService getService() {
        if (port == null) {

//        URL url = new URL(Boc2CrmAppServiceImplService.class.getResource("."),"http://localhost:8088/gboc?WSDL");
//        QName qname = new QName("http://service.boc2.bccs.viettel.com/","Boc2CrmAppServiceImplService");

            Boc2CrmAppServiceImplService service = new Boc2CrmAppServiceImplService();

            Boc2CrmAppService boc2CrmAppService = service.getBoc2CrmAppServiceImplPort();
            BindingProvider bindingProvider = (BindingProvider) boc2CrmAppService;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
//                    "http://10.58.71.144:8094/BOC2Services/boc2/Boc2CrmAppService");
                    "http://localhost:8088/gboc");

            List<Handler> handlerChain=new ArrayList<>();
//            handlerChain.add(new WebServiceHandler("Username","order"));
//            handlerChain.add(new WebServiceHandler("Password","order"));
            handlerChain.add(new AuthorHeaderHandler("UsernameToken-32ce50e8-4a5d-4040-af71-c3428d92daa7","order","order"));

            Binding binding = bindingProvider.getBinding();
            binding.setHandlerChain(handlerChain);


            port = boc2CrmAppService;
        }

        return port;
    }
//    public Wss4jSecurityInterceptor securityInterceptor() {
//        Wss4jSecurityInterceptor wss4jSecurityInterceptor = new Wss4jSecurityInterceptor();
//        wss4jSecurityInterceptor.setSecurementActions("UsernameToken");
//        wss4jSecurityInterceptor.setSecurementMustUnderstand(true);
//        wss4jSecurityInterceptor.setSecurementPasswordType("PasswordText");
//        wss4jSecurityInterceptor.setSecurementUsername("123456789011");
//        wss4jSecurityInterceptor.setSecurementPassword("TestPass123");
//        return wss4jSecurityInterceptor;
//    }
//    public void setHandlerChain(String key, String value) {
//        Binding bindingProvider=((BindingProvider) getService()).getBinding();
//        List<Handler> handlerChain=new ArrayList<>();
//        handlerChain.add(new WebServiceHandler(key,value));
//        bindingProvider.setHandlerChain(handlerChain);
//    }

    public WsResponseCrmApp getContactResultOfStaffSME(InputReportStaffSME inputReportStaffSME) throws Exception_Exception {
        try {
            return getService().getContactResultOfStaffSME(inputReportStaffSME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WsResponseCrmApp getReportListGroupKpi() throws Exception_Exception {
        try {
            return getService().getReportListGroupKpi();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WsResponseCrmApp getReportStaffInfoSME(InputReportStaffSME inputReportStaffSME) throws Exception_Exception {
        try {
            return getService().getReportStaffInfoSME(inputReportStaffSME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WsResponseCrmApp updateContactStatusSME(InputUpdateStatusSME inputUpdateStatusSME) throws Exception_Exception {
        try {
            return getService().updateContactStatusSME(inputUpdateStatusSME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WsResponseCrmApp getCustomerSME(InputCustomerSME inputCustomerSME) throws Exception_Exception {
        try {
            return getService().getCustomerSME(inputCustomerSME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WsResponseCrmApp getProgramsSME() throws Exception_Exception {
        try {
            return getService().getProgramsSME();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WsResponseCrmApp getAllAlarmKpiSME(InputSearchKpiSME inputSearchKpiSME) throws Exception_Exception {
        try {
            return getService().getAllAlarmKpiSME(inputSearchKpiSME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WsResponseCrmApp getReportStaffDetailSME(InputReportStaffSME inputReportStaffSME) throws Exception_Exception {
        try {
            return getService().getReportStaffDetailSME(inputReportStaffSME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
