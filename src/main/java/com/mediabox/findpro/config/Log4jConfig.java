package com.mediabox.findpro.config;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
//@ImportResource({ "classpath:log4j.xml" })
public class Log4jConfig {
	@Bean
    public ConsoleAppender consoleAppender() {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.WARN);
//        PatternLayout patternLayout = new PatternLayout();
//        patternLayout.setConversionPattern("%d %-5p  [%c{1}] %m %n");
//        consoleAppender.setLayout(patternLayout);
        return consoleAppender;
    }
	
	
}
