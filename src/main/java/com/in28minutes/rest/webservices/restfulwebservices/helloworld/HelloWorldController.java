package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

	private MessageSource messageSource;

	public HelloWorldController(MessageSource messageSource){
		this.messageSource = messageSource;

	}

	//@RequestMapping(method = RequestMethod.GET, path = "/hello-world") OR
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		
		return "HelloWorld";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public helloWorldBean helloWorldBean() {
		
		return new helloWorldBean("Hello World through Bean.");
	}
	
	//How bean are converted into JSON ?
	// 1. @ResponseBody() and JacksonHttpMessageConvertersConfiguration 
	
	
	//path parameters
	// /user/{id}/todos/{id} => /users/2/todos/200
	// /hello-world-bean/path-variable/{name} => /hello-world-bean/path-variable/{jagannath}
	
	@GetMapping(path = "/hello-world-bean/path-variable/{name}")
	public helloWorldBean helloWorldBeanVariable(@PathVariable String name) {
		
		return new helloWorldBean(String.format("Hello, %s", name));
	}


	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {

		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message",null,"Default Message",locale);

		//return "HelloWorld";

		//		- Example: `en` - English (Good Morning)
		//		- Example: `nl` - Dutch (Goedemorgen)
		//		- Example: `fr` - French (Bonjour)
		//		- Example: `de` - Deutsch (Guten Morgen)

	}

	/*Adding lines to test the commits.*/
}
