package br.com.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LimparArquivoDaPastaLocalStepConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step limparArquivoDaPastaLocalStep() {
		return stepBuilderFactory
				.get("limparArquivoDaPastaLocalStep")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("Pasta local esvaziada");
					return RepeatStatus.FINISHED;
				})
				.build();
	}
}	
