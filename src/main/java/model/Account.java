package model;

import java.time.LocalDateTime;

public abstract class Account {
    private Long id;
    private String number;
    private Double balance;
    private Long clientId;
    private LocalDateTime createdAt;

    /* ---------------Constructeurs------------------*/
    public Account() {
    }

    public Account(Long id, String number, Double balance, Long clientId) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.clientId = clientId;
    }

    /* ------Getter et setters ----------*/
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /*--------------------Méthodes---------------------*/

    public void credit(double amount){
        if (amount > 0){
            this.balance += amount;
        }
    }

    public void debit(double amount){
        if (amount > 0){
            this.balance -= amount;
        }
    }

    public abstract String getType();

    /*-------------affichage --------------*/
    @Override
    public String toString() {
        return "Account{" + id + "}\nNumber:" + number + "\nBalance: " + balance + "\nClient: " + clientId + "\nDate:" + createdAt;
    }
}
