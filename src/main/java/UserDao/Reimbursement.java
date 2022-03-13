package UserDao;

import jakarta.persistence.*;

@Entity
public class Reimbursement {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @SequenceGenerator(name = "project_seq", sequenceName = "project_seq", allocationSize = 1)
    private int id;
    private String requester;
    private String date;
    private double amount;
    private String reason;
    private String status;
    private String supportingDocuments;

    public Reimbursement(){}
    public Reimbursement(double amount, String requester,String reason, String supportingDocuments){
        this.amount = amount;
        this.requester = requester;
        this.reason = reason;
        this.supportingDocuments = supportingDocuments;
    }

    public int getId() {return id;}
    public void setId(int id){this.id = id;}

    public double getAmount() {return amount;}
    public void setAmount(double amount){this.amount = amount;}

    public String getRequester(){return requester;}
    public void setRequester(String requester) {this.requester = requester;}

    public String getReason(){return reason;}
    public void setReason(String reason) {this.reason = reason;}

    public String getStatus(){return status;}
    public void setStatus(String status) {this.status = status;}

    public String getSupportingDocuments() {return supportingDocuments;}
    public void setSupportingDocuments(String supportingDocuments) {this.supportingDocuments = supportingDocuments;}

    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
}
