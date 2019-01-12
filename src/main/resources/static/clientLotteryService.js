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
       
        <button class="btn btn-primary" onclick="openRegistrationWindow(${lottery.id})">Register To Lottery</button>
    
        </td>
    `;
        document.getElementById("table-body").appendChild(tr);
    }
    else {

    }
}
function openRegistrationWindow(id) {
    window.location.href="/registeringParticipant.html?lotteryId=" + id;
}

function addParticipantToLottery() {
    const lotteryId = new URL(window.location.href).searchParams.get("lotteryId");
    const email = document.getElementById("email").value;
    const age = document.getElementById("age").value;
    const code = document.getElementById("code").value;

    fetch("/register", {
        method: "post",
            body: JSON.stringify({
                lotteryId: lotteryId,
                email: email,
                age: age,
                code: code
            }),
        headers:{
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then(resp => resp.json());
    alert("You have been successfully registered ");
    window.location.href = "/availableLotteries.html"
}