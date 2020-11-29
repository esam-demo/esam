package com.esam.util;

/**
 * @author trutao
 * @create 2020-11-27 0:14
 */
public class SecurityUtils {
    private SecurityUtils() {
    }

//    public static String getCurrentUserId() {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        String userId = null;
//        if (authentication != null) {
//            if (authentication.getPrincipal() instanceof UserDetails) {
//                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
//                userId = springSecurityUser.getUsername();
//            } else if (authentication.getPrincipal() instanceof String) {
//                userId = (String) authentication.getPrincipal();
//            }
//        }
//        return userId;
//    }
}
