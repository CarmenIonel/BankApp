package Application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by Ionel Carmen on 3/30/2017.
 */
@Configuration
@EnableWebMvc
public class MVCConfig extends WebMvcConfigurerAdapter
{
    @Bean
    public InternalResourceViewResolver viewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/Pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable();
    }

    public void addViewControllers(ViewControllerRegistry reg)
    {
       //login
        reg.addViewController("/login").setViewName("/login");
        reg.addViewController("/").setViewName("/login");

        //admin
        reg.addViewController("/adminMeniu").setViewName("adminMeniu");
        reg.addViewController("/deleteEmployee").setViewName("deleteEmployee");
        reg.addViewController("/createEmployee").setViewName("createEmployee");
        reg.addViewController("/updateEmployee").setViewName("/updateEmployee");
        reg.addViewController("/viewEmployee").setViewName("viewEmployee");
        reg.addViewController("/getReport").setViewName("getReport");
        //employee
        reg.addViewController("/employeeMeniu").setViewName("employeeMeniu");
        reg.addViewController("/createClientAccount").setViewName("createClientAccount");
        reg.addViewController("/updateAccount").setViewName("updateAccount");
        reg.addViewController("/deleteAccount").setViewName("deleteAccount");
        reg.addViewController("/viewAccount").setViewName("viewAccount");
        reg.addViewController("/updateClient").setViewName("updateClient");
        reg.addViewController("/deleteClient").setViewName("deleteClient");
        reg.addViewController("/viewClient").setViewName("viewClient");
        reg.addViewController("/payBill").setViewName("payBill");
        reg.addViewController("/transfer").setViewName("transfer");
    }
}
