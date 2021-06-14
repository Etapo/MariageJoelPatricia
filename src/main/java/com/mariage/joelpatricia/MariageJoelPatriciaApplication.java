package com.mariage.joelpatricia;

import com.mariage.joelpatricia.utils.Encryption;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages = {"com.mariage.joelpatricia"})
public class MariageJoelPatriciaApplication extends SpringBootServletInitializer {
    
    public static Encryption encryption = null;

    public static void main(String[] args) {
        try {
            MariageJoelPatriciaApplication.encryption = new Encryption("JoelPatr@#100721");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MariageJoelPatriciaApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        SpringApplication.run(MariageJoelPatriciaApplication.class, args);
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MariageJoelPatriciaApplication.class);
    }

}
