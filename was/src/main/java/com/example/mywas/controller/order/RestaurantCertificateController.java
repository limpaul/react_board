package com.example.mywas.controller.order;

import com.raonsecure.ksbiz.KSBizException;
import com.raonsecure.ksbiz.KSBiz_v2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RestaurantCertificateController {
    @Autowired private HttpServletRequest request;
    @RequestMapping("/raonnx/ksbiz/jsp/nonce")
    public String getNonce() {
        KSBiz_v2 ksobj = new KSBiz_v2();
        try{
            ksobj.libInit();
            if(request.getMethod().equalsIgnoreCase("GET")){
                return ksobj.genNonce();
            }
            return ksobj.genNonce();
        }catch (KSBizException e){
            return e.toString();
        }
    }
}
