package model;

import java.time.LocalDateTime;

public class Operation {
    private Long id;
    private String type;
    private Double amount;
    private LocalDateTime operationDate;
    private Long accountId;

    /*-------------Constructeurs----------------*/
    public Operation() {
    }

    public Operation(Long id, String type, Double amount, LocalDateTime operationDate, Long accountId) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.operationDate = operationDate;
        this.accountId = accountId;
    }

    /*-----------------getter et setter --------------------------*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDateTime operationDate) {
        this.operationDate = operationDate;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
