package com.flol.semrankerengine.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flol.semrankercommon.domain.Keyword;

@RestController
public class TestController {

    private final AtomicLong counter = new AtomicLong();
    private final Logger log = LoggerFactory.getLogger(this.getClass());    
    
    
    @RequestMapping("/keyword")
    public Keyword keyword(@RequestParam(value="text", defaultValue="World") String text) {
    	log.info("Chiamata funzione keyword");
    	System.out.println(System.getProperty("LOG_FILE"));
    	
    	Keyword keyword = new Keyword();
    	keyword.setId(counter.incrementAndGet());
    	keyword.setText(text);
    	return keyword;
    }
	
}
