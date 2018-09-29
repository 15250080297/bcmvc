package com.bc.controller;


import com.alibaba.fastjson.JSONObject;
import com.bc.common.SystemCode;
import com.bc.common.enums.ActionEnum;
import com.bc.common.enums.RouterEnum;
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

    //private String URL="http://127.0.0.1:8080/2/tool";  //dev
   // private String URL="http://182.92.3.98:8080/2/tool";  //qa
   private String URL="https://api.beecloud.cn/2/tool";     //prod

    /**
     * 对账单
     */
    private String URL_BJ2="http://123.56.92.236:8080/2/tool";
    //private String URL_BJ2="http://127.0.0.1:8080/2/tool";  //dev


    /**
     * COINS API
     */
     private String COINS_URL="http://182.92.3.98:3587/tool";  //qa


    @RequestMapping(value="/post",method = RequestMethod.POST)
    public ResponseWithData post(@RequestParam String action, @RequestParam String param){
        if(action.startsWith("/coins"))
            return cpDone(COINS_URL,action.substring(6),param);



        ActionEnum actionEnum=null;
        try{
            actionEnum=ActionEnum.valueOf(action);
        }catch (Exception e){

        }
        if(null!=actionEnum)
            return action(actionEnum,param);

        return postDone(URL,action,param);
    }


    private ResponseWithData action(ActionEnum actionEnum,String param){
        switch (actionEnum){
            case GET_LOGIN_AUTHINFO:
                return getInfo();
            case RECONCILITION_FILES:
                return postDone(URL_BJ2,"/reconcilition/files",param);
            case RECONCILITION_DELFILE:
                return postDone(URL_BJ2,"/reconcilition/delfile",param);
            case RECONCILITION_GENERATE:
                return postDone(URL_BJ2,"/reconcilition/generate",param);
            default:
                return new ResponseWithData(SystemCode.CUSTOM_ERROR.getCode(),"暂不支持");
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
        for(RouterEnum routerEnum:RouterEnum.values()){
            routerList.add(routerEnum.getPath());
        }
        info.setRouterNames(routerList);
        info.setMenuList(MenuDto.menus());
        for(MenuDto menuDto:info.getMenuList()){
            logger.info(JSONObject.toJSONString(menuDto));
        }
        return new ResponseWithData(SystemCode.SUCCESS.getCode(), SystemCode.SUCCESS.getMessage(),info);

    }


    public ResponseWithData postDone(String realUrl,String action,String param){
        JSONObject jsonObject=null;
        try {
            String src= HttpClientUtil.postWithParams(realUrl + action, param);
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


    public ResponseWithData cpDone(String realUrl,String action,String param){
        JSONObject jsonObject=null;
        try {
            String src= HttpClientUtil.postWithParams(realUrl + action, param);
            if(StringUtils.isNotBlank(src))
                jsonObject=JSONObject.parseObject(src);
        }catch (Exception e){
        }
        if(null==jsonObject||!jsonObject.containsKey("resultCode")){
            return new ResponseWithData(SystemCode.LINK_ERROR.getCode(), SystemCode.LINK_ERROR.getMessage());
        }
        int code=jsonObject.getInteger("resultCode");
        if(0==code){
            return new ResponseWithData(SystemCode.SUCCESS.getCode(), SystemCode.SUCCESS.getMessage(),jsonObject);
        }else{
            return new ResponseWithData(code, jsonObject.getString("errorMsg"));
        }

    }
}
