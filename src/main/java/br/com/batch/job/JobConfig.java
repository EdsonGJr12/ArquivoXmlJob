package br.com.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job leitorXMLJob(Step downloadXmlParaPastaLocalStep, Step leitorXmlStep, Step limparArquivoDaPastaLocalStep) {
		return jobBuilderFactory
				.get("leitorXMLJob")
				.start(downloadXmlParaPastaLocalStep)
				.next(leitorXmlStep)
				.next(limparArquivoDaPastaLocalStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
