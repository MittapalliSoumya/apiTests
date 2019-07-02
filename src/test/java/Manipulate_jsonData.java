import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Manipulate_jsonData {

    //JSON ARRAY =>[] - list
    // JSON object =>{} - map


    String payload = "{\"store\":{\"book\":[{\"category\":\"reference\",\"author\":\"Nigel Rees\",\"title\":\"Sayings of the Century\",\"price\":8.95},{\"category\":\"fiction\",\"author\":\"Evelyn Waugh\",\"title\":\"Sword of Honour\",\"price\":12.99},{\"category\":\"fiction\",\"author\":\"Herman Melville\",\"title\":\"Moby Dick\",\"isbn\":\"0-553-21311-3\",\"price\":8.99},{\"category\":\"fiction\",\"author\":\"J. R. R. Tolkien\",\"title\":\"The Lord of the Rings\",\"isbn\":\"0-395-19395-8\",\"price\":22.99}],\"bicycle\":{\"color\":\"red\",\"price\":19.95}},\"expensive\":10}";


    @Test
    public void simpleJsonObjectMap() {
        String Payload1 = "{\"employee\":{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}}";

        ReadContext ctx = JsonPath.parse(Payload1);

        Map<String, String> TestsList = ctx.read("$.employee");

        System.out.println(TestsList.get("name"));

        //map get element => json object => key value pair
    }

    @Test
    public void simpleJsonArrayList() {

        String payload1 = "{\"name\":\"Admin\",\"age\":36,\"rights\":[\"admin\",\"editor\",\"contributor\"]}";

        ReadContext ctx = JsonPath.parse(payload);

        List<String> TestsList = ctx.read("$.rights", List.class);

        System.out.println(TestsList.contains("editor"));
        //get elements in array => jsonarray
    }


    @Test
    public void multipleListwithMapJsondata() {
        ReadContext ctx = JsonPath.parse(payload);
        List<Map<String, String>> TestsList = ctx.read("$..book[?(@.price <= $['expensive'])]", List.class);

        for (Map mapDetails : TestsList) {
            if (mapDetails.get("category").equals("reference")) {
                System.out.println("price :" + mapDetails.get("price"));
                break;
            }
        }

    }


    @Test
    public void listListWithmapData() {

        ReadContext ctx = JsonPath.parse(payload);

        List<Object> TestsList = ctx.read("$.store.*", List.class);

        //above object is java object
        for (Object test : TestsList) {

            if (test instanceof Map<?, ?>) {
                System.out.println(((Map) test).get("color"));

            } else if (test instanceof List<?>) {
                for (Object li : ((List) test).toArray()) {
                    if (li instanceof Map<?, ?>) {
                        Map li_cast = (Map) li;
                        if ((li_cast.containsKey("category") && (li_cast.get("category").equals("fiction")))) {
                            System.out.println("Title: " + li_cast.get("title") + " Price: " + li_cast.get("price"));
                        }
                    }
                }
            }
        }
    }


    @Test
    public void NestingJsonGetData() {

        ReadContext ctx = JsonPath.parse(payload);

        List<Object> TestsList = ctx.read("$.store.*", List.class);

        for (Object test : TestsList) {

            if (test instanceof Map<?, ?>) {
                System.out.println("bicycle price : " + ((Map) test).get("price"));

            } else if (test instanceof List<?>) {
                for (Object list : ((List) test)) {
                    if (list instanceof Map<?, ?>) {
                        Map convertObjToMap = (Map) list;
                        if (convertObjToMap.containsKey("title") && convertObjToMap.get("title").equals("Sword of Honour")) {
                            System.out.println("price " + convertObjToMap.get("price"));
                        }
                    }


                }

            }

        }
    }



    @Test
    public void leastUsedCC(){

        String response = "{\"users\":[\"John\",\"Alice\",\"Dave\",\"Jean\"],\"cc\":[\"American express\",\"Chase Saphire\",\"Amazon Prime\"],\"cc_to_users\":[[0,0],[1,1],[1,2],[2,0]]}";

        ReadContext ctx = JsonPath.parse(response);

        List<List> TestsList = ctx.read("$.cc_to_users", List.class);
        List<List> cc = ctx.read("$.cc", List.class);

        List cckeys = new ArrayList();

        for (List li: TestsList) {
        if(!cckeys.contains(li.get(0))){
            cckeys.add(li.get(0));
        }else{
            cckeys.remove(li.get(0));
        }
        }

        System.out.println(cckeys.get(0));

        System.out.println(cc.get((Integer) cckeys.get(0)));
    }
}
