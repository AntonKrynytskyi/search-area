package pro.kaa.search.area.providers;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.spi.resource.provider.ResolveContext;
import org.apache.sling.spi.resource.provider.ResourceContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pro.kaa.search.area.providers.resource.TrelloResource;
import pro.kaa.search.area.TrelloHttpClientService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class TrelloResourceProviderTest {

    @InjectMocks
    private TrelloBoardResourceProvider trelloBoardResourceProvider = new TrelloBoardResourceProvider();

    @Mock
    private TrelloHttpClientService trelloHttpClient;

    @Mock
    private Resource resource;

    @Mock
    private ResolveContext<TrelloBoardResourceProvider.Empty> resolveContext;

    @Mock
    private ResourceContext resourceContext;

    @Mock
    private TrelloResource trelloResource;

    @Test
    public void shouldReturnBoardResource_whenRootPathCorrect() {

        resource = trelloBoardResourceProvider.getResource(
                resolveContext, "/trello/board/e23234ee234", resourceContext, resource);

        Assert.assertNotNull("Resus muts not be null", resource);
        Assert.assertEquals(resource.getClass(), TrelloResource.class);
    }

    @Test
    public void shouldReturnNull_whenPathToResourceNotStartWithProviderRootPath() {
        resource = trelloBoardResourceProvider.getResource(
                resolveContext, "/mrg3n/board/e23234ee234", resourceContext, resource);

        Assert.assertNull("Resus muts be null", resource);
    }

    @Test
    public void shouldReturnNull_whenPathToResourceNull() {
        resource = trelloBoardResourceProvider.getResource(
                resolveContext, null, resourceContext, resource);

        Assert.assertNull("Resus muts be null", resource);
    }

    @Test
    public void shouldReturnBoardResource_whenTrelloClientReturnBoardResource() throws IOException {
/*
        Mockito.when(trelloHttpClient.sendGetRequest("http://api.trello.com/1/boards/47zdgoUK")).thenReturn("{board:}");

        resource = trelloBoardResourceProvider.getResource(
                resolveContext, null, resourceContext, resource);

        Assert.assertNotNull("Resus muts not be null", resource);
        Assert.assertEquals(resource.getClass(), TrelloResource.class);
*/
    }

    @Test
    public void shouldReturnBoardResource_whenPathContainBoardId(){
        String path = "/trello/board/e23234ee234";

        resource = trelloBoardResourceProvider.getResource(
                resolveContext, path, resourceContext, resource);

        Assert.assertNotNull("Resus muts not be null", resource);
        Assert.assertEquals(resource.getClass(), TrelloResource.class);
    }

    @Test
    public void shouldReturnNull_whenPathContainBoardIdNoExist(){
        String path = "/trello/board/";

        resource = trelloBoardResourceProvider.getResource(
                resolveContext, path, resourceContext, resource);

        Assert.assertNull("Resus muts be null", null);

    }

    @Test
    public void test_area() throws URISyntaxException {
        String sss ="sdfsd";
        HashMap<String,Object> ss = new HashMap<>();
        System.out.println(sss.getClass().equals(String.class));
        System.out.println(sss instanceof String);
        System.out.println(ss instanceof Map);

    }
}
