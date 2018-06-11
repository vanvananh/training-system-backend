package com.cmc.training.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cmc.training.dto.AuthorizationUtilzationDTO;
import com.cmc.training.dto.AuthorizationUtilzationWrapper;
import com.cmc.training.entity.Role;
import com.cmc.training.service.PermissionService;
import com.cmc.training.service.RolePermissionService;
import com.cmc.training.util.Constants;

@Controller
public class LoginController {
	
  @Autowired
  PermissionService permissionService;

  @Autowired
  RolePermissionService rolePermissionService;

  @RequestMapping(value = "/")
  private ModelAndView home(Model model, HttpSession session, SessionStatus status) {
    Object user = session.getAttribute(Constants.SESSION_USER);
    ModelAndView mav = new ModelAndView();
    mav.setViewName("login");
    if (user != null && user instanceof UserDetails) {
      Collection<? extends GrantedAuthority> auths = ((UserDetails) user).getAuthorities();

      if (auths != null && auths.stream()
          .anyMatch(ga -> ga.getAuthority().equals(com.cmc.training.util.Role.ADMIN.getName()))) {
        status.setComplete();
        session.removeAttribute("message");
        List<AuthorizationUtilzationDTO> authorizationUtilzationDTOs = new ArrayList<AuthorizationUtilzationDTO>();
        List<Role> roles = new ArrayList<Role>();
        AuthorizationUtilzationWrapper authorizationUtilzationWrapper = new AuthorizationUtilzationWrapper();
        try {
          authorizationUtilzationDTOs = permissionService.getRolePermission();
          roles = permissionService.getAllRole();
          authorizationUtilzationWrapper = new AuthorizationUtilzationWrapper(
              rolePermissionService.getAllRolePermission());
          model.addAttribute("permissionTotals", authorizationUtilzationDTOs);
          model.addAttribute("roles", roles);
          model.addAttribute("rolePermissions", authorizationUtilzationWrapper);
        } catch (SQLException e) {
          e.printStackTrace();
          model.addAttribute("permissionTotals", authorizationUtilzationDTOs);
          model.addAttribute("roles", roles);
          model.addAttribute("rolePermissions", authorizationUtilzationWrapper);
          mav.setViewName("error/500");
          return mav;
        }

        mav.setViewName("admin");
        return mav;
      }
    }
    return mav;
  }

}
