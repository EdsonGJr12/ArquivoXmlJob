package br.com.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DownloadXmlParaPastaLocalStepConfig{
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step downloadXmlParaPastaLocalStep() {
		return stepBuilderFactory
				.get("downloadXmlStep")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("XMl baixado");
					return RepeatStatus.FINISHED;
				})
				.build();
	}
	 

}
