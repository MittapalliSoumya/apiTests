import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;


public class RestUtils {

   private String Path;

    RestUtils(String path,String baseURI){
        this.Path = path;
        RestAssured.baseURI = baseURI;
    }

    static Logger log = Logger.getLogger(RestUtils.class.getName());

    public Response postRequest(String payload) throws ExceptionHandling{
        log.info("======> Entered post Request <=========");
        return  given().contentType("application/json")
                        .accept("application/json")
                        .body(payload)
                        .post(Path);
    }

    public Response putRequest(String payload,String blobId ){
        log.info("======> Entered put Request <=========");
        return given()
                .contentType("application/json")
                .accept("application/json")
                .body(payload)
                .put(Path + blobId);
    }

    public Response getRequest(String blobId){
        log.info("======> Entered get Request <=========");
        return given()
                .contentType("application/json")
                .accept("application/json")
                .get(Path + blobId);

    }

    public Response deleteRequest(String payload,String blobId){
        log.info("======> Entered delete Request <=========");
        return given().contentType("application/json")
                     .accept("application/json")
                     .body(payload)
                     .delete(Path + blobId);

    }

}

