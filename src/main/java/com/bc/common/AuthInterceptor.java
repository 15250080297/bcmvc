package com.bc.common;


import com.bc.response.Response;
import com.bc.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.UUID;

/**
 * Created by beijixiong on 2017/6/2.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static Logger logger= Logger.getLogger(AuthInterceptor.class);



    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
      //  httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
       // httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
       // httpServletResponse.setHeader("Access-Control-Max-Age", "1800");//30 min
       // httpServletResponse.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,Token,Origin, X-Requested-With, Content-Type, Accept");
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "*");
        logger.info(httpServletRequest.getMethod());
        logger.info(httpServletRequest.getHeaderNames().toString());
      /*  for(Enumeration<String> enumeration: httpServletRequest.getHeaderNames().){

        }*/
      /*  Enumeration<String> headerNames = (Enumeration<String>) httpServletResponse.getHeaderNames();
        //获取获取的消息头名称，获取对应的值，并输出
        while(headerNames.hasMoreElements()){
            String nextElement = headerNames.nextElement();
            logger.info(nextElement+":"+httpServletResponse.getHeader(nextElement));
        }*/
        if(httpServletRequest.getMethod().equals("OPTIONS")){
            Response response = new Response(SystemCode.SUCCESS.getCode(),SystemCode.SUCCESS.getMessage());
            printResponse(httpServletResponse,response);
            return false;
        }
        logger.info(httpServletRequest.getMethod());
        return true;
    //    }
      /*  errorOutput(httpServletResponse,token);
        return false;*/
    }

    private void printResponse(HttpServletResponse httpServletResponse,Response response){
        PrintWriter out = null;
        try {
            out = httpServletResponse.getWriter();
            out.append(JsonUtil.convertObjectToString(response));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }



    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
