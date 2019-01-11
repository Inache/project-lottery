package lv.inache.projectLottery.lottery.lotteryResponses;

public class StartRegistrationResponse {
    private String status;
    private Long id;
    private String reason;

    public StartRegistrationResponse(String status, Long id) {
        this.status = status;
        this.id = id;
    }
    public StartRegistrationResponse(String status, String reason){
        this.status = "Fail";
        this.reason = reason;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
