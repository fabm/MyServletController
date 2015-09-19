package pt.ipg.ei.servlets.path.content;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import pt.ipg.ei.servlets.uri.UriWithoutContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RequestScoped
public class PathAnalyser {
    @Inject
    private UriWithoutContext uriWithoutContext;
    private Map<String, Object> map;
    private List<PathContent> pathContentList;

    @Inject
    private void init() {
        pathContentList = new ArrayList<PathContent>();
        map = new HashMap<String, Object>();
    }

    public PathAnalyser resetVars(){
        pathContentList.clear();
        return this;
    }

    public Object getValue(String key) {
        return map.get(key);
    }

    public PathAnalyser stringVar() {
        pathContentList.add(new StringVar());
        return this;
    }

    public PathAnalyser intVar() {
        pathContentList.add(new IntVar());
        return this;
    }

    public PathAnalyser longVar() {
        pathContentList.add(new LongVar());
        return this;
    }

    public boolean matches(String pathToMatch) {
        char[] path = uriWithoutContext.getPath().toCharArray();
        char[] cPath2Match = pathToMatch.toCharArray();
        int ip2m = 0;
        boolean scape = false;
        boolean inVar = false;
        StringBuilder stringBuilder = null;
        Iterator<PathContent> it = pathContentList.iterator();
        for (int i = 0; i < cPath2Match.length; i++) {
            char curr = cPath2Match[i];


            boolean beginVar = !scape && curr == '{';
            scape = curr == '\\';
            inVar = inVar || beginVar;


            if (beginVar) {
                stringBuilder = new StringBuilder();
            } else if (inVar && curr != '}') {
                stringBuilder.append(curr);
            } else if (inVar && curr == '}') {
                if (!it.hasNext()) {
                    return false;
                }
                final PathContent pathContent = it.next();
                final StringBuilder varValue = new StringBuilder();
                ip2m = capture(varValue, path, ip2m);
                if (!pathContent.matches(varValue.toString())) {
                    return false;
                }
                map.put(stringBuilder.toString(), pathContent.getValue());
                inVar = false;
            } else {
                if (!(ip2m < path.length) || curr != path[ip2m]) {
                    return false;
                }
                ip2m++;
            }
        }
        return !it.hasNext();
    }

    private int capture(StringBuilder varValue, char[] cPathToMatch, int currIndex) {
        boolean isDivisor = false;
        while (!isDivisor) {
            varValue.append(cPathToMatch[currIndex]);
            if (!(++currIndex < cPathToMatch.length)) {
                return currIndex;
            }
            isDivisor = cPathToMatch[currIndex] == '/';
        }
        return currIndex;
    }

}
