package test.java.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.json.simple.JSONObject;
import utils.Config;

public class ConfigTest implements Config {

  JSONObject config;

  @Test
  public void testParserBasic() {
    assertNotNull(parseConfig("config.json"));
  }

  @Test
  public void testParserNull() {
    try {
      JSONObject json = parseConfig(null);
    } catch (NullPointerException e) {
      return;
    }
    throw new AssertionError();
  }

  @Test
  public void confToIntegerArrListTestBasic() {
    JSONObject json = parseConfig("config.json");
    ArrayList<Integer> arrIntList = new ArrayList<Integer>();
    arrIntList.add(180);
    arrIntList.add(180);
    arrIntList.add(180);
    arrIntList.add(180);
    arrIntList.add(180);
    arrIntList.add(180);
    arrIntList.add(180);
    arrIntList.add(180);
    assertEquals(confToIntegerArrList(json, "modeLengths"), arrIntList);
  }

  @Test
  public void confToIntegerArrListTestNull() {
    assertNull(confToIntegerArrList(null, null));

    assertNull(confToIntegerArrList(null, "key"));

    JSONObject json = parseConfig("config.json");
    assertNull(confToIntegerArrList(json, null));
  }

  @Test
  public void confToIntegerTest() {
    JSONObject json = parseConfig("config.json");
    assertEquals(confToInteger(json, "frightenedLength"), 3);
  }

  @Test
  public void confToIntegerTestNull() {
    assertNull(confToInteger(null, null));

    assertNull(confToInteger(null, "key"));

    JSONObject json = parseConfig("config.json");
    assertNull(confToInteger(json, null));
  }

  @Test
  public void confToStringTest() {
    JSONObject json = parseConfig("config.json");
    assertEquals(confToString(json, "map"), "map.txt");
  }

  @Test
  public void confToStringTestNull() {
    assertNull(confToString(null, null));

    assertNull(confToString(null, "key"));

    JSONObject json = parseConfig("config.json");
    assertNull(confToString(json, null));
  }
}
