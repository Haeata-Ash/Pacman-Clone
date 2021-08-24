package utils;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import java.io.Reader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
* This interface provides functionality to read json config files and JSONObjects.
*/
public interface Config {

  /**
  * Reads a json file and returns its representation as a JSONObject.
  * @param filename A relative path to a json file.
  * @return A JSONObject.
  * @see JSONObject
  */
  default JSONObject parseConfig(String filename) {
    if (filename == null) {
      throw new NullPointerException();
    }
    JSONParser parser = new JSONParser();

    try {
      Reader reader = new FileReader(filename);
      JSONObject json = (JSONObject) parser.parse(reader);
      return json;

    } catch (FileNotFoundException e) {
      System.out.println("Config file not found.");
      System.exit(1);
    } catch (IOException e) {
      System.out.println("Unable to read config file.");
      System.exit(1);
    } catch (ParseException e) {
      System.out.println("Unable to read config file.");
      System.exit(1);
    }

    return null;
  }

  /**
  * Retrieves a JSONObject entry and turns it into an integer arraylist.
  * @param json A JSONObject.
  * @param key A key corresponding to an entry in the JSONObject.
  * @return An ArrayList of Integers.
  */
  default ArrayList<Integer> confToIntegerArrList(JSONObject json, String key) {
    if (json == null || key == null) {
      return null;
    }

    JSONArray list = (JSONArray) json.get(key);
    Iterator<Long> iter = list.iterator();
    Long l;
    Integer elem;
    ArrayList<Integer> intArrList = new ArrayList<Integer>();

    while (iter.hasNext()) {
      l = (Long) iter.next();
      elem = Integer.valueOf(l.intValue()) * 60;
      intArrList.add(elem);
    }

    return intArrList;
  }

  /**
  * Retrieves a JSONObject entry and turns it into an integer.
  * @param json A JSONObject.
  * @param key A key corresponding to an entry in the JSONObject.
  * @return An Integer.
  */
  default Integer confToInteger(JSONObject json, String key) {
    if (json == null || key == null) {
      return null;
    }

    Long l = (Long) json.get(key);
    return Integer.valueOf(l.intValue());
  }

  /**
  * Retrieves a JSONObject entry and turns it into an String.
  * @param json A JSONObject.
  * @param key A key corresponding to an entry in the JSONObject.
  * @return An Integer.
  */
  default String confToString(JSONObject json, String key) {
    if (json == null || key == null) {
      return null;
    }

    String s = (String) json.get(key);
    return s;
  }

}
