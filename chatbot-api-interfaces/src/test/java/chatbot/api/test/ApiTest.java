package chatbot.api.test;


import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @description 单元测试
 */
public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/15555548421822/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie", "zsxq_access_token=42742E08-9261-FD34-BBFA-F8DB75D12072_A08B58C0518F8DF0; zsxqsessionid=db6fc978bff1c43878ad160cae5060ce; abtest_env=product; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross={\"distinct_id\":\"585182441424244\",\"first_id\":\"18e83ff3e8388a-067fd30cfe3e82-61095e50-1327104-18e83ff3e84b56\",\"props\":{},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThlODNmZjNlODM4OGEtMDY3ZmQzMGNmZTNlODItNjEwOTVlNTAtMTMyNzEwNC0xOGU4M2ZmM2U4NGI1NiIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjU4NTE4MjQ0MTQyNDI0NCJ9\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"585182441424244\"},\"$device_id\":\"18e83ff3e8388a-067fd30cfe3e82-61095e50-1327104-18e83ff3e84b56\"}");
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/4844818522518258/answer");
        post.addHeader("cookie", "zsxq_access_token=42742E08-9261-FD34-BBFA-F8DB75D12072_A08B58C0518F8DF0; zsxqsessionid=db6fc978bff1c43878ad160cae5060ce; abtest_env=product; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross={\"distinct_id\":\"585182441424244\",\"first_id\":\"18e83ff3e8388a-067fd30cfe3e82-61095e50-1327104-18e83ff3e84b56\",\"props\":{},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThlODNmZjNlODM4OGEtMDY3ZmQzMGNmZTNlODItNjEwOTVlNTAtMTMyNzEwNC0xOGU4M2ZmM2U4NGI1NiIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjU4NTE4MjQ0MTQyNDI0NCJ9\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"585182441424244\"},\"$device_id\":\"18e83ff3e8388a-067fd30cfe3e82-61095e50-1327104-18e83ff3e84b56\"}");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"2！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/chat/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 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");

        String paramJson = "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"帮我写一个java冒泡排序\"}],\n" +
                "     \"temperature\": 1024\n" +
                "   }";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

}

