package com.chatgpt.springbootchatgpt;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import okhttp3.OkHttpClient;





@SpringBootApplication
@RestController
public class SpringBootChatgptApplication {
	CallChatGpt callChatGpt = new CallChatGpt();

	@PostMapping("/query")
	public String query(@RequestBody Map<String, String> request) {
		String question = request.get("question");
		System.out.println(question);
		// Call ChatGPT API and extract answer
		String answer = callChatGpt.function(question);

		// Append question and answer to CSV file
		try {
			FileWriter writer = new FileWriter("results.csv", true);
			writer.append(question + ";" + answer + "\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Return answer to the user
		return answer;
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringBootChatgptApplication.class, args);
	}

}
