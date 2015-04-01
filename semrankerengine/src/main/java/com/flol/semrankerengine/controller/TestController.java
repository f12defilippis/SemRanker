package com.flol.semrankerengine.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flol.semrankercommon.domain.Keyword;

@RestController
public class TestController {

    private final AtomicLong counter = new AtomicLong();
    
    @RequestMapping("/keyword")
    public Keyword keyword(@RequestParam(value="text", defaultValue="World") String text) {

    	Keyword keyword = new Keyword();
    	keyword.setId(counter.incrementAndGet());
    	keyword.setText(text);
    	return keyword;
    }
	
}
