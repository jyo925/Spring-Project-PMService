package com.project.bit.foo.appConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.project.bit.foo.service.MyUserDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyUserDetailService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/registration","/userStatusList").permitAll()          //userStatus Test 위해 추가함
				.antMatchers("/addDuty","/addMembers","/calendar","/calendarE").hasAnyAuthority("duty100","duty200","duty300","duty400")
				.antMatchers("/authorityConfig/**").hasAuthority("duty100")
				.antMatchers("/adminMain","/main", "/main/outputAllStatus", "/teamSettingList", "/getTeamDetail", "/updateTeam", "/admin/**/**", "/admin/userSetting", "/goProjectAdd", "/project/detailInfo/**", "/projectMember/**", "/dashBoardAll", "/dashBoardDetail/**").hasAnyAuthority("duty100","duty200")
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.usernameParameter("USER_ID")
				.passwordParameter("USER_PW")
				.failureHandler(customAuthenticationFailureHandler())
				.permitAll()
			.and()
				.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login")
			.and()
				.csrf().disable();
		
		
	}
	
	@Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new loginFailureHandler();
    }

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
	
	 @Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/resources/**",
										"/static/**",
										"/css/**",
										"/images/**",
										"/js/**", 
										"/fullcalendar/**/**",
										"/mapper/**",
										"/lib/**/**/**");
		}

	public WebSecurityConfiguration() {
		// TODO Auto-generated constructor stub
	}

}
