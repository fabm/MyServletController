package pt.common.libs.servlet.controllers;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import pt.ipg.ei.servlets.path.content.PathAnalyser;

import javax.inject.Provider;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class MyServletControllers extends HttpServlet {

    @Inject
    private Provider<PathAnalyser> pathMatcherProvider;

    @Inject
    private Provider<MyController> myControllerProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PathAnalyser pathAnalyser = pathMatcherProvider.get();
        resp.getWriter().write(matching(pathAnalyser));
    }

    private String matching(PathAnalyser pathAnalyser) {
        boolean isMatchedOlaMundo = pathAnalyser
            .intVar()
            .intVar()
            .stringVar()
            .matches("/ola/mundo/{var1}/{var2}/{x}");

        if (isMatchedOlaMundo) {
            Integer var1 = (Integer) pathAnalyser.getValue("var1");
            Integer var2 = (Integer) pathAnalyser.getValue("var2");
            String x =  pathAnalyser.getValue("x").toString();
            return myControllerProvider.get().index(var1,var2,x);
        }

        boolean isMatchedTest = pathAnalyser
                .resetVars()
                .matches("/ola/test");

        if(isMatchedTest){
            return myControllerProvider.get().teste();
        }

        return "none";
    }


}
