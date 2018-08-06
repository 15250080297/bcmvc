package com.bc.controller;


import com.alibaba.fastjson.JSONObject;
import com.bc.common.SystemCode;
import com.bc.dto.InfoDto;
import com.bc.dto.MenuDto;
import com.bc.response.ResponseWithData;
import com.bc.util.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by  on 2017/6/28.
 */
@RestController
@RequestMapping("/mvc")
public class CommonController  {
    private static Logger logger = Logger.getLogger(CommonController.class);

    private String URL="http://127.0.0.1:8080/2/tool";

    @RequestMapping(value="/post",method = RequestMethod.POST)
    public ResponseWithData post(@RequestParam String action, @RequestParam String param){
        logger.info("post in"+action);
        logger.info("post in"+param);

        if("GetInfo".equals(action))
            return getInfo();

        JSONObject jsonObject=null;
        try {
           String src= HttpClientUtil.postWithParams(URL + action, param);
           if(StringUtils.isNotBlank(src))
               jsonObject=JSONObject.parseObject(src);
        }catch (Exception e){
        }
        if(null==jsonObject||!jsonObject.containsKey("result_code")){
            return new ResponseWithData(SystemCode.LINK_ERROR.getCode(), SystemCode.LINK_ERROR.getMessage());
        }
        int code=jsonObject.getInteger("result_code");
        if(0==code){
            return new ResponseWithData(SystemCode.SUCCESS.getCode(), SystemCode.SUCCESS.getMessage(),jsonObject);
        }else{
            return new ResponseWithData(code, jsonObject.getString("err_detail"));
        }
    }

    public ResponseWithData getInfo(){
        logger.info("getInfo in");
        InfoDto info=new InfoDto();
        info.setName("wzq");
        info.setToken("wzqtesttoken");
        info.setPrivileges(new ArrayList<>());
        info.setRealName("test name");
        List<String> routerList=new ArrayList<>();
        routerList.add("pcManage");
        routerList.add("requisition");
        routerList.add("purchase");
        routerList.add("pcReceipt");
        routerList.add("pcShouldPay");
        routerList.add("pcPayment");
        routerList.add("pcSettle");
        routerList.add("plugins");
        routerList.add("demolist");
        routerList.add("privilegeDemo");
        routerList.add("requisitionAdd");
        routerList.add("test");
        info.setRouterNames(routerList);
        info.setMenuList(MenuDto.menus());

        return new ResponseWithData(SystemCode.SUCCESS.getCode(), SystemCode.SUCCESS.getMessage(),info);

    }
}