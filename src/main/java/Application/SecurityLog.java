package Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Ionel Carmen on 3/30/2017.
 */
@Configuration
public class SecurityLog extends WebSecurityConfigurerAdapter
{

        //@Autowired
        //private UserVerification uv;

        public void configure(HttpSecurity hs) throws Exception
        {
                hs.csrf().disable();
                hs.authorizeRequests().antMatchers("/WEB-INF/Pages/**").permitAll();
//                    .antMatchers("/login").permitAll()
//                    .anyRequest().authenticated().and()
//                    .formLogin().defaultSuccessUrl("/homepage",true)
//                    .loginPage("/login").
//                    usernameParameter("u").
//                    passwordParameter("p").
//                    permitAll().and().
//                    logout().permitAll();
        }
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder amb) throws Exception
        {
            //amb.userDetailsService(this.uv);
        }
}
