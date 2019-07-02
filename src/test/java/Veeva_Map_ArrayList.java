import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class Veeva_Map_ArrayList {

  @Test
    public void convertMapToArrayList(){
      String payload = "{\"data\":{\"name\":\"Objectlifecyclestagegroup\",\"class\":\"metadata\",\"label\":\"Object Lifecycle Stage Group\",\"label_plural\":\"Object Lifecycle Stage Groups\",\"abbreviation\":\"OSG\",\"active\":true,\"cacheable\":true,\"cache_type_class\":\"veeva.ecm.vcf.domain.cache.DefaultVcfRecordCacheType\",\"attributes\":[{\"name\":\"label\",\"type\":\"String\",\"requiredness\":\"optional\",\"max_length\":60,\"editable\":true,\"multi_value\":false,\"active\":true,\"queryable\":false},{\"name\":\"active\",\"type\":\"Boolean\",\"requiredness\":\"optional\",\"editable\":true,\"multi_value\":false,\"active\":true,\"queryable\":false},{\"name\":\"object_type\",\"label\":\"Object Type\",\"type\":\"Component\",\"requiredness\":\"required\",\"editable\":false,\"multi_value\":false,\"component\":\"Objecttype\",\"active\":true,\"queryable\":false},{\"name\":\"description\",\"label\":\"Description\",\"type\":\"String\",\"requiredness\":\"optional\",\"max_length\":255,\"editable\":true,\"multi_value\":false,\"active\":true,\"queryable\":false},{\"name\":\"object_lifecycle\",\"label\":\"Object Lifecycle\",\"type\":\"Component\",\"requiredness\":\"required\",\"editable\":false,\"multi_value\":false,\"component\":\"Objectlifecycle\",\"active\":true,\"queryable\":false}],\"sub_components\":[{\"name\":\"Objectlifecyclestage\",\"label\":\"Object Lifecycle Stage\",\"label_plural\":\"Object Lifecycle Stages\",\"abbreviation\":\"OLS\",\"active\":true,\"json_collection_name\":\"Objectlifecyclestage\",\"attributes\":[{\"name\":\"label\",\"type\":\"String\",\"requiredness\":\"optional\",\"max_length\":60,\"editable\":true,\"multi_value\":false,\"active\":true,\"queryable\":false},{\"name\":\"active\",\"type\":\"Boolean\",\"requiredness\":\"optional\",\"editable\":true,\"multi_value\":false,\"active\":true,\"queryable\":false},{\"name\":\"object_lifecycle_states_ref\",\"label\":\"Object Lifecycle States\",\"type\":\"String\",\"requiredness\":\"optional\",\"max_length\":1500,\"editable\":true,\"multi_value\":true,\"ordered\":false,\"active\":true,\"queryable\":false},{\"name\":\"description\",\"label\":\"Description\",\"type\":\"String\",\"requiredness\":\"optional\",\"max_length\":255,\"editable\":true,\"multi_value\":false,\"active\":true,\"queryable\":false},{\"name\":\"order\",\"label\":\"Order\",\"type\":\"Number\",\"requiredness\":\"required\",\"max_value\":2147483647,\"min_value\":1,\"scale\":0,\"editable\":true,\"multi_value\":false,\"active\":true,\"queryable\":false}]}]},\"responseStatus\":\"SUCCESS\"}";

      ReadContext ctx = JsonPath.parse(payload);

      List<Map<String,Object>> TestsList = ctx.read("$.data.attributes", List.class);

      for (Map mapDetails:TestsList) {


      }



      System.out.println();

  }



}
