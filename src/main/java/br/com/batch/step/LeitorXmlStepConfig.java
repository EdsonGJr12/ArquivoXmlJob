package br.com.batch.step;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import br.com.batch.domain.NotaFiscal;

@Configuration
public class LeitorXmlStepConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step leitorXmlStep(ItemReader<NotaFiscal> xmlItemReader,
			ItemWriter<NotaFiscal> xmlItemWriter) {
		return stepBuilderFactory
				.get("leitorXmlStep")
				.<NotaFiscal, NotaFiscal>chunk(1)
				.reader(xmlItemReader)
				.writer(xmlItemWriter)
				.build();
	}
	
	@Bean
	@StepScope
	public StaxEventItemReader<NotaFiscal> xmlItemReader(@Value("#{jobParameters['arquivoClientes']}") Resource arquivoXml) {
		return new StaxEventItemReaderBuilder<NotaFiscal>()
				.name("xmlItemReader")
				.resource(arquivoXml)
				.addFragmentRootElements("nfe")
				.unmarshaller(tradeMarshaller())
				.build();
	}
	
	@Bean
	public ItemWriter<NotaFiscal> xmlItemWriter() {
		 return items -> items.forEach(System.out::println);
	}
	
	@Bean
	@SuppressWarnings("rawtypes")
	public XStreamMarshaller tradeMarshaller() {
		Map<String, Class> aliases = new HashMap<>();
		aliases.put("nfe", NotaFiscal.class);
		aliases.put("numero", Integer.class);
		aliases.put("valor", BigDecimal.class);
		aliases.put("tomador", String.class);
		XStreamMarshaller marshaller = new XStreamMarshaller();
		marshaller.setAliases(aliases);
		return marshaller;
	}
	
	
	
}
