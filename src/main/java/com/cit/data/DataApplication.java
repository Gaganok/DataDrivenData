package com.cit.data;

import com.cit.data.component.TestDataComponent;
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
	private void postConstruct() throws FileNotFoundException {
		Reader reader = new StringReader(tdc.getTestData());//fileArg.isEmpty() ?  new StringReader(tdc.getTestData()) :  new FileReader(new File(fileArg));
		BufferedReader bf = new BufferedReader(reader);
		//System.out.println(tdc.getTestData());
		tdc.testFileData = bf;
	}
}
