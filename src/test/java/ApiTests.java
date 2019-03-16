import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests extends BaseTest {


     private String  payload = "{\"people\":[\"bill\", \"steve\", \"bob\"]}";
     private String  updatedPayload = "{\"people\":[\"soumya\", \"arun\", \"aryan\"]}";
     private String  blobId;

    @Test
    public void createNewBlogPost() throws ExceptionHandling{

        try {
            Response res = restUtils.postRequest(payload);

            log.info("created new blog post");

            //Validate that blog post was created (verify status code is 200 & content-type is application/json)
            Assert.assertEquals(res.getStatusCode(),201,"Correct status code not returned");
            Assert.assertTrue(res.getHeader("Content-Type").contains("application/json"));

            // Extract blogId from Response Header
            blobId = res.getHeader("Location").split("https://jsonblob.com/api/jsonBlob/")[1].toString();
        } catch(Exception e){
            throw new ExceptionHandling("test");
        }

    }


    @Test(dependsOnMethods={"createNewBlogPost"}, alwaysRun=true)
    public void updateBlogpost(){

         Response res = restUtils.putRequest(updatedPayload,blobId);

         Assert.assertEquals(res.getStatusCode(),200,"Correct status code not returned");

         Response json = restUtils.getRequest(blobId);

         Assert.assertEquals(json.getBody().asString(),updatedPayload);
    }

    @Test(dependsOnMethods={"updateBlogpost"}, alwaysRun=true)
    public void deleteBlogpost(){

        Response res = restUtils.deleteRequest(payload,blobId);
        Assert.assertEquals(res.getStatusCode(),200,"Correct status code not returned");
    }


}
