package Zorkgreus;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class testH {
    private static ArrayList<Boon> boons;
    private static Parser parser;
    public static void main(String[] args) {
        try {
            initBoons("src\\Zorkgreus\\data\\boons.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        parser = new Parser();
        System.out.println(jsonBoons.get(16));
    }
    public static void initBoons(String file) throws Exception {
        boons = new ArrayList<Boon>();
        Path path = Path.of(file);
        String jsonString = Files.readString(path);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonString);
    
        JSONArray jsonBoons = (JSONArray) json.get("boons");
    
        for (Object boonObj : jsonBoons) {
          Boon boon = new Boon();
          String godName = (String) ((JSONObject) boonObj).get("god");
          String boonName = (String) ((JSONObject) boonObj).get("name");
          String decorativeText = (String) ((JSONObject) boonObj).get("colour");
          String stat = (String) ((JSONObject) boonObj).get("stat");
          int level = Math.toIntExact((Long) ((JSONObject) boonObj).get("level"));
          boon.setGod(godName);
          boon.setBoonName(boonName);
          boon.setColour(decorativeText);
          boon.setStats(stat);
          boon.setLevel(level);
          boons.add(boon);
        }
      }
}


