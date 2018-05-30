package br.com.abgsolucoes.sigaweb.tests.util;


import java.io.File;
import java.io.FileNotFoundException;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

public class Junit4ClassRunner extends SpringJUnit4ClassRunner {

	static {
		try {
		/*	Log4jConfigurer.initLogging(new File(System.getProperty("user.dir")).getAbsolutePath() + File.separator
					+ "src" + File.separator + "main" + File.separator + "resources" + File.separator + "br"
					+ File.separator + "com" + File.separator + "abgsolucoes" + File.separator + "seguranca" + File.separator
					+ "log4j.properties");*/
			Log4jConfigurer.initLogging(new File(System.getProperty("user.dir")).getAbsolutePath() + File.separator
					+"src"+File.separator+"test"+File.separator+"resource"+ File.separator+"log4j.properties");
		} catch (FileNotFoundException ex) {
			System.err.println("Cannot Initialize log4j");
		}
	}

	public Junit4ClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

}