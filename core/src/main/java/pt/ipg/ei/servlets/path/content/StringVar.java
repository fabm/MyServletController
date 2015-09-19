package pt.ipg.ei.servlets.path.content;

public class StringVar implements PathContent{
    private String value;

    @Override
    public boolean matches(String value) {
        if(value == null){
            return false;
        }
        if(value.isEmpty()){
            return false;
        }
        this.value = value;
        return true;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
