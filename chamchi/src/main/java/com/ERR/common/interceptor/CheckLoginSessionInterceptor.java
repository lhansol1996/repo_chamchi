package com.ERR.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ERR.common.constants.Constants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckLoginSessionInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (request.getSession().getAttribute("sessMemberSeq") != null) {
			// by pass 관리자 확인
		} else {
			response.sendRedirect(Constants.URL_XDMLOGINFORM);
	        return false;
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
