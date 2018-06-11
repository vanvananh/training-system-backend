package com.cmc.training.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.cmc.training.util.Constants;
import com.cmc.training.util.MethodUtil;

import javax.servlet.http.HttpSession;

/**
 * @author 
 */
@Component
public class UserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    /**
     * The Logger for this class.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpSession httpSession;

    /**
     * A Spring Security UserDetailsService implementation based upon the Account
     * entity model.
     */
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token)
            throws AuthenticationException {
        logger.debug("> additionalAuthenticationChecks");

        String[] pwds = userDetails.getPassword().split(" ");

        if (!isValidPassword((String) token.getCredentials(), pwds[1], pwds[0])) {
            throw new BadCredentialsException("Invalid credentials.");
        }

        logger.debug("< additionalAuthenticationChecks");
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token)
            throws AuthenticationException {
        logger.debug("> retrieveUser");

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        logger.debug("< retrieveUser");
        return userDetails;
    }

    /**
     * So sanh password request va trong db
     *
     * @param reqPpwd mat khau tu request cua user
     * @param salt    chuoi dung de ma hoa mat khau
     * @param dbPass  mat khau tu database
     * @return
     */
    private boolean isValidPassword(String reqPpwd, String salt, String dbPass) {
        String hash_pass = MethodUtil.sha1(salt.concat(MethodUtil.sha1(reqPpwd)));
        return hash_pass.equals(dbPass);
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
                                                         UserDetails user) {
        httpSession.setAttribute(Constants.SESSION_USER, user);
        return super.createSuccessAuthentication(principal, authentication, user);
    }

}
