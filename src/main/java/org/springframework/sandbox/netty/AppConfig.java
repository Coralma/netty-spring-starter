package org.springframework.sandbox.netty;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ImportResource({"classpath*:/applicationContext.xml"})
public class AppConfig {
}
