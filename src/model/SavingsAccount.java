package model;

public class SavingsAccount extends Account {
    private double interestRate;

    /*----------------Constructeurs-------------------------*/
    public  SavingsAccount() {
        super();
    }
    public SavingsAccount(Long id, String number,double balance, Long clientID, double interestRate) {
        super(id, number, balance, clientID);
        this.interestRate = interestRate;
    }

    /*-------------------getter/setter---------------*/
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String getType(){
        return "SAVINGS";
    }
}
