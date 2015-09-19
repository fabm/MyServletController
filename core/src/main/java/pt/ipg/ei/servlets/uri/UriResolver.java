package pt.ipg.ei.servlets.uri;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.copyOfRange;

@RequestScoped
public class UriResolver {
    @Inject
    private HttpServletRequest request;
    private String context;
    private List<String> split;

    @Inject
    private void init() {
        context = request.getContextPath();
        final String uri = request.getRequestURI();
        String afterContext = uri.substring(context.length(), uri.length());
        String[] spaths = afterContext.split("/");
        if (spaths.length > 0 && spaths[0].isEmpty()) {
            spaths = copyOfRange(spaths, 1, spaths.length);
        }

        split = asList(spaths);
    }

    public String getContext() {
        return context;
    }

    public List<String> getSplit() {
        return split;
    }
}
