function loadLotteries() {
    fetch("/lotteries",{
        method: "get"
    }).then(resp => resp.json()
    ).then(lotteries =>{
        for (const lottery of lotteries){
            addLottery(lottery);
        }
    });
}

function addLottery(lottery) {
    if (lottery.registrationIsAvailable == true) {
        const tr = document.createElement("tr");
        tr.innerHTML = `
        <td>${lottery.id}</td>
        <td>${lottery.title}</td>
        <td>${lottery.participantsLimit}</td>
        <td>
       
        <button class="btn btn-primary" onclick="registerToLottery(${lottery.id})">Register To Lottery</button>
    
        </td>
    `;
        document.getElementById("table-body").appendChild(tr);
    }
    else {

    }
}
function registerToLottery(id) {
    window.location.href="/registeringParticipant.html?lotteryId" + id;
    //U have been succesfully registered to lottery with ID TROLOLOLO GOODLUCK
}