package pt.common.libs.servlet.controller.injection;

import com.google.inject.*;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import org.codehaus.jackson.map.ObjectMapper;
import pt.common.libs.servlet.controllers.MyServletControllers;

import javax.inject.Singleton;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

@WebListener
public class SBGuiceServletListener extends GuiceServletContextListener {
    Logger logger = Logger.getLogger(SBGuiceServletListener.class.getName());

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(ObjectMapper.class).toProvider(new Provider<ObjectMapper>() {
                    @Override
                    public ObjectMapper get() {
                        return new ObjectMapper();
                    }
                }).in(Singleton.class);
            }
        }, new ServletModule() {
            @Override
            protected void configureServlets() {
                logger.info("configurating Servlet");
                serve("/*", "/").with(MyServletControllers.class);
            }
        });
    }
}
