package model;

public class CurrentAccount  extends Account {
    private double overdraftLimit;

    /* --------------Constructeurs---------------*/
    public CurrentAccount(){
        super();
    }

    public CurrentAccount(Long id, String number, double balance, Long clientId, double overdraftLimit) {
        super(id, number, balance, clientId);
        this.overdraftLimit = overdraftLimit;
    }

    /* -------------getters et setters-------------------*/

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    /*----------------méthodes----------------------*/
    @Override
    public String getType() {
        return "CURRENT";
    }

    public boolean canWithdraw(double amount) {
        return (getBalance() + overdraftLimit) >= amount;
    }

    /*--------------affichage--------------*/
    @Override
    public String toString() {
        return "CuurentAccount{" + getId() +"}\nNumber:" + getNumber() + "\nBalance: " + getBalance() + "\nOverdraft Limit: " + getOverdraftLimit();
    }

}
