import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

public class UrlTest {
    private Module getModule(){
        return new AbstractModule() {
            @Override
            protected void configure() {
                final HttpServletRequest mock = Mockito.mock(HttpServletRequest.class);
                bind(HttpServletRequest.class).toInstance(mock);
            }
        };
    }
    @Test
    public void urlTest() {
        Injector inj = Guice.createInjector(getModule());
        HttpServletRequest ins = inj.getInstance(HttpServletRequest.class);

    }
}
