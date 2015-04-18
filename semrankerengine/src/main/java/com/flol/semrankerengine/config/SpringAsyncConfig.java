package com.flol.semrankerengine.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.flol.semrankercommon.domain.SearchengineParameter;
import com.flol.semrankercommon.repository.SearchengineParameterRepository;

@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer {
     
	@Autowired
	private SearchengineParameterRepository searchengineParameterRepository;
	
	@Override
    @Bean(name = "threadPoolTaskExecutor")
    public Executor getAsyncExecutor() {

		SearchengineParameter maxThreadParameter = searchengineParameterRepository.findOne("NUM_MAX_THREADS");

		Integer maxThread = Integer.valueOf(maxThreadParameter.getValue());
		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(maxThread);
        executor.setMaxPoolSize(maxThread);
        executor.setQueueCapacity(0);
        executor.setThreadNamePrefix("SemRankerExecutor-");
        executor.initialize();
        return executor;
    }

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return null;
	}
     
}
