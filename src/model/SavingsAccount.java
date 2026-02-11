package model;

public class SavingsAccount extends Account {

    public  SavingsAccount() {
        super();
    }

    @Override
    public String getType(){
        return "SAVINGS";
    }
}
