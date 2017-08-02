package com.goldgov.origin.webgate;

import org.springframework.boot.SpringApplication;

import com.goldgov.origin.core.OriginApplication;

public class Starter extends OriginApplication{
	
    public static void main( String[] args ) {
    	SpringApplication.run(new Object[]{Starter.class}, args);
    }

}
