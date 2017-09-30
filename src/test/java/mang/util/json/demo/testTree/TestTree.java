package mang.util.json.demo.testTree;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TestTree {

	@Test
	public void testTree() throws IOException {
		String jsonString = "{\"k1\":\"v1\",\"k2\":\"v2\"}";
		ObjectMapper mapper = new ObjectMapper();

		JsonNode actualObj = mapper.readTree(jsonString);
		String v1 = actualObj.get("k1").asText();
		assertEquals(v1, "v1");
		assertNotNull(actualObj);
	}

	@Test
	public void testTree2() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode root = JsonNodeFactory.instance.objectNode();
		root.put("int", 5);
		root.with("other").put("type", "student");

		ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
		arrayNode.add("java");
		arrayNode.add("druid");

		root.set("arrayNode", arrayNode);

		ObjectNode node2 = mapper.createObjectNode();
		node2.put("hello", "world");
		root.with("other").put("node2", node2);

		String json = mapper.writeValueAsString(root);
		System.out.println(json);
	}

	@Test
	public void testTree3() throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File("c:/user.json");
		JsonNode root = mapper.readTree(file);
		

		Long id = root.path("id").asLong();
		System.out.println(id);

		// Get Name
		JsonNode nameNode = root.path("name");
		if (nameNode.isMissingNode()) {
			// if "name" node is missing
		} else {

			String firstName = nameNode.path("first").asText();
			// missing node, just return empty string
			String middleName = nameNode.path("middle").asText();
			String lastName = nameNode.path("last").asText();

			System.out.println("firstName : " + firstName);
			System.out.println("middleName : " + middleName);
			System.out.println("lastName : " + lastName);

		}

		// Get Contact
		JsonNode contactNode = root.path("contact");
		if (contactNode.isArray()) {
			// If this node an Arrray?
		}

		for (JsonNode node : contactNode) {
			String type = node.path("type").asText();
			String ref = node.path("ref").asText();
			System.out.println("type : " + type);
			System.out.println("ref : " + ref);

		}
	}
	
	
	@Test
	public void testTree4() throws JsonProcessingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		File file = new File("c:/user2.json");
		JsonNode rootArray = mapper.readTree(file);
		
		for(JsonNode root : rootArray){
			String firstName=root.at("/name/first").asText();
			
			String firstName2=root.path("name").path("first").asText();
			System.out.println(firstName);
			System.out.println(firstName2);
			
		}
		
	}
	
}
