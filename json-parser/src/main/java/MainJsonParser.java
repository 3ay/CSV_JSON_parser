import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainJsonParser {
    public static String readString(String jsonFile)
    {
        JSONParser parser = new JSONParser();
        String result = "";
        try {
            Object obj = parser.parse(new FileReader(jsonFile));
            if (obj instanceof JSONObject jsonObject) {
                result = jsonObject.toJSONString();
            } else if (obj instanceof JSONArray jsonArray) {
                result = jsonArray.toJSONString();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
            return result;
    }
    public static List<Employee> jsonToList(String jsonString) {
        List<Employee> employeeList = new ArrayList<>();

        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(jsonString);

            Gson gson = new GsonBuilder().create();
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                Employee employee = gson.fromJson(jsonObject.toJSONString(), Employee.class);
                employeeList.add(employee);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return employeeList;
    }
    public static void main(String[] args) {
        String json = readString("new_data.json");
        List<Employee> list = jsonToList(json);
        System.out.println(list);
    }
}
