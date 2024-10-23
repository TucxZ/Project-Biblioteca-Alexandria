package br.ifsul.bdii;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Main{

	public static void main(String[] args) {

		SpringApplicationBuilder sab = new SpringApplicationBuilder(Main.class);
		sab.headless(false);
		sab.run(args);
	}

}
