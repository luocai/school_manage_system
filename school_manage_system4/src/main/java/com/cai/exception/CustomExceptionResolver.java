package com.cai.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object,
			Exception exception) {
		ModelAndView modelAndView = new ModelAndView();
		CustomException customException;
		if (exception instanceof CustomException){
			customException = (CustomException)exception;
		}else if (exception instanceof UnknownAccountException){
			modelAndView.addObject("message", "账号不存在");
			modelAndView.setViewName("error");
			return modelAndView;
		}else if (exception instanceof IncorrectCredentialsException){
			modelAndView.addObject("message", "密码错误");
			modelAndView.setViewName("error");
			return modelAndView;
		}else{
			customException = new CustomException("未知错误");
		}
		
		 //错误信息
        String message = customException.getMessage();



        //错误信息传递和错误页面跳转
        modelAndView.addObject("message", message);
        modelAndView.setViewName("error");


        return modelAndView;
	}

}
