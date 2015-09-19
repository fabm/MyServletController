package pt.ipg.ei.servlets.path.content;

public interface PathContent {
    boolean matches(String value);
    Object getValue();
}
