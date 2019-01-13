package lv.inache.projectLottery.lottery;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LotteryResponse {
    private String status;
    private Long id;
    private String reason;
    private String winnersCode;

    public LotteryResponse(String status, Long id) {
        this.status = status;
        this.id = id;
    }

    public LotteryResponse(String status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    //Cheat to create constructor with same arguments
    public LotteryResponse(String status, String winnersCode, boolean cheat) {
        this.status = status;
        this.winnersCode = winnersCode;
    }

    public LotteryResponse(String status) {
        this.status = status;
    }

    public String getWinnersCode() {
        return winnersCode;
    }

    public void setWinnersCode(String winnersCode) {
        this.winnersCode = winnersCode;
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
