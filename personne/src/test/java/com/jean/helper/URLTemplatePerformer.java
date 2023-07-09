package com.jean.helper;

import com.busi.person.domain.ProposePerson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class URLTemplatePerformer {
    public static void post(ProposePerson proposed, String mapping, MockMvc mockMvc) throws Exception {
        mockMvc.perform( setMetaAndExpectation(MockMvcRequestBuilders.post(mapping),proposed) );
    }

    public static void put(ProposePerson proposed, String mapping, MockMvc mockMvc) throws Exception {
        mockMvc.perform( setMetaAndExpectation(MockMvcRequestBuilders.put(mapping),proposed) );
    }

    public static void delete(String mapping, MockMvc mockMvc) throws Exception {
        mockMvc.perform( MockMvcRequestBuilders.delete(mapping) );
    }

    public static <T> T get(Class<T> klass, String mapping, MockMvc mockMvc) throws Exception {
        MvcResult result  = mockMvc
                .perform(MockMvcRequestBuilders.get(mapping))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .readValue(contentAsString, klass);
    }

    private static RequestBuilder setMetaAndExpectation(MockHttpServletRequestBuilder builder, ProposePerson proposed) throws JsonProcessingException {
        return builder.content(convertObjectToJson(proposed))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    private  static String convertObjectToJson(Object object) throws JsonProcessingException {

        ObjectWriter ow = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writer()
                .withDefaultPrettyPrinter();

        return ow.writeValueAsString(object);
    }
}
