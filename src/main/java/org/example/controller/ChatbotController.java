package org.example.controller;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatbotController {
    private final ChatLanguageModel chatModel;
    private final EmbeddingModel embeddingModel;


    @Autowired
    public ChatbotController(ChatLanguageModel chatModel, EmbeddingModel embeddingModel) {
        this.chatModel = chatModel;
        this.embeddingModel = embeddingModel;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String message) {
        String response = chatModel.generate(message);
        return response;
    }

    @PostMapping("/embed")
    public List<Float> embed(@RequestBody String text) {
        Embedding embedding = embeddingModel.embed(text).content();
        return embedding.vectorAsList();
    }
}
