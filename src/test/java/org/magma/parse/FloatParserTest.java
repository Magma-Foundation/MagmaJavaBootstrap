package org.magma.parse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloatParserTest {
	private final ObjectMapper mapper = new ObjectMapper();
	private final Parser parser = new FloatParser(mapper);

	@Test
	void parseWithoutIdentifier() {
		JsonNode node = parser.parse("10.0").orElseThrow();
		assertEquals("float", node.get("type").asText());

		//JsonNode doesn't have a .asFloat() method. :(
		assertEquals(10.0, node.get("value").asDouble());
	}

	@Test
	void parseWithIdentifier() {
		JsonNode node = parser.parse("10.0f").orElseThrow();
		assertEquals("float", node.get("type").asText());

		//JsonNode doesn't have a .asFloat() method. :(
		assertEquals(10.0, node.get("value").asDouble());
	}
}