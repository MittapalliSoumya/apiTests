import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manipulate_jsonData {


   // RestUtils restUtils =  new RestUtils(PATH);


    @Test
    public void readjsondata() {
        String Payload1 = "{\"employee\":{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}}";

        ReadContext ctx = JsonPath.parse(Payload1);

        List<Map<String, String>> TestsList = ctx.read("$..employee", List.class);

        Map<String,String> newmap =null;

        for (Map mapobj: TestsList) {
            newmap = new HashMap<String, String>();
            newmap.put("name",mapobj.get("name").toString());
            newmap.put("age",mapobj.get("age").toString());
        }
        System.out.println(newmap);
    }

    @Test
    public  void verifyJsonArray(){

        String payload = "{\"name\":\"Admin\",\"age\":36,\"rights\":[\"admin\",\"editor\",\"contributor\"]}";

        ReadContext ctx = JsonPath.parse(payload);

        List<Map<String, String>> list = new ArrayList<>();

        List<String> TestsList = ctx.read("$..rights", List.class);


        System.out.println(TestsList+ "===");


    }


    @Test
    public void multiplejsondata() {

    }
}
