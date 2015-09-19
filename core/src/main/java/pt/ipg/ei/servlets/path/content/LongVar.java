package pt.ipg.ei.servlets.path.content;

public class LongVar implements PathContent{
    private Long value;

    @Override
    public boolean matches(String value) {
        if(value == null){
            return false;
        }
        try {
            this.value = Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Long getValue() {
        return value;
    }
}
