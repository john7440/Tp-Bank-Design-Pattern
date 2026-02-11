package model;

public class CurrentAccount  extends Account {

    public CurrentAccount(){
        super();
    }

    @Override
    public String getType() {
        return "CURRENT";
    }
}
