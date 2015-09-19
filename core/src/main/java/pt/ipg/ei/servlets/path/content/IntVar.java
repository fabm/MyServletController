package pt.ipg.ei.servlets.path.content;

public class IntVar implements PathContent{
    private Integer value;

    @Override
    public boolean matches(String value) {
        if(value == null){
            return false;
        }
        try {
            this.value = Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
