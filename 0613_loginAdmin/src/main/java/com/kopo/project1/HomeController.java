package com.kopo.project1;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.List; 


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		DB db = new DB();
		
		if (!db.isTableExists("user")) {
	        db.createTable();
	        db.createAdminAccount(); 
	    }
		
		return "index";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Locale locale, Model model) {
		return "join";
	}


	@RequestMapping(value = "/join_action", method = RequestMethod.POST)
	public String joinAction(Locale locale, Model model
			, HttpServletRequest request
			) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		DB db = new DB();
		db.insertData(new User(id, pwd, name, phone, address));
		return "redirect:/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		return "login";
	}

	@RequestMapping(value = "/login_action", method = RequestMethod.POST)
	public String loginAction(Locale locale, Model model
			, HttpServletRequest request, HttpSession session
			) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		DB db = new DB();
		User loggedUser = db.login(new User(id, pwd));
		if (loggedUser.userType.isEmpty()) {
			session.setAttribute("is_login", false);
			session.setAttribute("user_type", "");
		} else {
			session.setAttribute("is_login", true);
			session.setAttribute("user_type", loggedUser.userType);
		}
		return "redirect:/";
	}

	
	@RequestMapping(value = "/user_list", method = RequestMethod.GET)
	public String userList(Locale locale, Model model, HttpSession session) {
		boolean isLogin = false;
		String userType = "";

		try {
			Object loginObj = session.getAttribute("is_login");
			Object typeObj = session.getAttribute("user_type");
			
			if (loginObj != null) {
				isLogin = (Boolean)loginObj;
			}
			if (typeObj != null) {
				userType = (String)typeObj;
			}
		} catch (Exception e) {
			e.printStackTrace();
			isLogin = false;
			userType = "";
		}

		if (isLogin) {
			if ("admin".equals(userType)) {
				try {
					DB db = new DB();
					List<User> userList = db.getAllUsers();
					model.addAttribute("userList", userList);
					System.out.println("Admin user accessed user list. Total users: " + userList.size());
					return "user_list";
				} catch (Exception e) {
					e.printStackTrace();
					return "redirect:/";
				}
			} else {
				System.out.println("Non-admin user tried to access user list. User type: " + userType);
				return "redirect:/";
			}
		} else {
			System.out.println("Unauthenticated user tried to access user list.");
			return "redirect:/";
		}
	}
}

