package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 1. handler 종류 확인
		if (handler instanceof HandlerMethod == false) {
//			System.out.println(handler);
			return true;
		}

		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. method에 @auth 없으면
		// Class(type)에 @Auth 를 받아오기
//		if(auth == null) {
//			..
//		}

		// 5. @Auth가 안 붙어 있는 경우
		if (auth == null) {
			return true;
		}

		// 6. class던 method던 @auth가 붙어있는 경우
		HttpSession session = request.getSession();
		if (session == null) { // 인증이 안돼있음.
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		//7. Role 가져오기
//		Auth.Role role = auth.role();
		
		//8. role이 Auth.Role.USER 아면
		// 	인증된 모든 사용자는 접근 가능
//		if ( role == Auth.Role.USER) {
//			return true;
//		}
		
		//9. admin인 경우
		// authUser.getRole.equals("ADMIN")
		//
		
		return true;
	}

}
