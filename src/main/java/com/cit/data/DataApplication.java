package com.cit.data;

import com.cit.data.component.TestDataComponent;
import com.cit.data.dao.Reddit;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.*;

@SpringBootApplication
public class DataApplication {
	private static String fileArg = "";
	public static void main(String[] args) {
		/*if(args.length > 0)
			fileArg = args[0];*/
		SpringApplication.run(DataApplication.class, args);
	}

	@Autowired private TestDataComponent tdc;
	@PostConstruct
	private void postConstruct() throws FileNotFoundException, JsonProcessingException {
		Reader reader = new StringReader(tdc.getTestData());//fileArg.isEmpty() ?  new StringReader(tdc.getTestData()) :  new FileReader(new File(fileArg));
		BufferedReader bf = new BufferedReader(reader);
		//System.out.println(tdc.getTestData());
		tdc.testFileData = bf;

		//Jackson Test
		/*Reddit reddit = new Reddit("g0l1o6,[OC] Website about covid-19 pandemic stats with several data visualizations/Graph types,1,muddymind,,moderator,0.0,[],1586791506,https://www.reddit.com/r/dataisbeautiful/comments/g0l1o6/oc_website_about_covid19_pandemic_stats_with/,3,False\n");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		String jsonReddit = objectMapper.writeValueAsString(reddit);
		System.out.println(jsonReddit);
		System.out.println();*/
	}
}
