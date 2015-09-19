package pt.ipg.ei.servlets.uri;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;

import javax.servlet.http.HttpServletRequest;

@RequestScoped
public class UriWithoutContext {
    @Inject
    private HttpServletRequest request;
    private String context;
    private String path;

    @Inject
    private void init() {
        context = request.getContextPath();
        final String uri = request.getRequestURI();
        path = uri.substring(context.length(), uri.length());
    }

    public String getContext() {
        return context;
    }

    public String getPath() {
        return path;
    }
}
