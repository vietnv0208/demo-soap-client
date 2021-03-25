package com.example.demo.api;

import com.example.demo.service.request.AuthorHeaderHandler;
import com.viettel.bccs.boc2.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.List;

@Service
public class GbocServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(GbocServiceImpl.class);

    private static Boc2CrmAppService port;

    @Value("${viettel.gboc.url}")
    private String url;
    @Value("${viettel.gboc.token}")
    private String token;
    @Value("${viettel.gboc.username}")
    private String username;
    @Value("${viettel.gboc.password}")
    private String password;

    private Boc2CrmAppService getService() {
        if (port == null) {
            Boc2CrmAppServiceImplService service = new Boc2CrmAppServiceImplService();

            Boc2CrmAppService boc2CrmAppService = service.getBoc2CrmAppServiceImplPort();
            BindingProvider bindingProvider = (BindingProvider) boc2CrmAppService;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    url);

            List<Handler> handlerChain = new ArrayList<>();
            handlerChain.add(new AuthorHeaderHandler(token, username, password));

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

    public List<ReportGroupKpi> getReportListGroupKpi() {
        try {
            WsResponseCrmApp responseCrmApp= getService().getReportListGroupKpi();
            if(responseCrmApp!=null){
                return responseCrmApp.getListGroupKpi();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    public WsResponseCrmApp getReportStaffInfoSME(InputReportStaffSME inputReportStaffSME) {
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

    public List<ProgramBO> getProgramsSME() {
        try {
            WsResponseCrmApp responseCrmApp= getService().getProgramsSME();
            if(responseCrmApp!=null){
                return responseCrmApp.getListProgram();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

        }
        return new ArrayList<>();
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
