package com.chatgpt.springbootchatgpt;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;

import java.io.IOException;

public class CallChatGpt {
    public String function(String question) {

        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.openai.com/v1/engines/davinci/jobs")
                    .post(RequestBody.create(MediaType.parse("application/json"), "{prompt; " + question + "}"))
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "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")
                    .build();



            Response response = client.newCall(request).execute();

            return response.body().string();



        } catch (IOException e) {
            e.printStackTrace();
            return "Error calling ChatGPT API";
        }

    }
}
