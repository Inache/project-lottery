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
    const tr = document.createElement("tr");
    tr.innerHTML = `
        <td>${lottery.id}</td>
        <td>${lottery.title}</td>
        <td>${lottery.participantsLimit}</td>
        <td>${lottery.registrationIsAvailable}</td>
        <td>${lottery.startDate}</td>
        <td>${lottery.endDate}</td>
        <td>${lottery.winnerCode}</td>
        <td>
       
        <button class="btn btn-primary" onclick="stopRegistration(${lottery.id})">STOP</button>
    
        </td>
    `;
    document.getElementById("table-body").appendChild(tr);
}

function stopRegistration(id) {
    fetch("/stop-registration",{
        method: "post",
        body: JSON.stringify({
            id: id
        }),
        headers:{
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then(()=>{
        window.location.reload();
    });

}
function saveCredentials() {

}

function createLottery() {
    const title = document.getElementById("title").value;
    const participantsLimit = document.getElementById("participantsLimit").value;
    fetch("/start-registration", {
        method: "post",
        body: JSON.stringify({
            title: title,
            participantsLimit: participantsLimit,
        }),
        headers:{
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then(()=>{
        window.location.href = "/lotteryList.html"
    });
}

function loadLotteryForEdit() {
    const lotteryId = new URL(window.location.href).searchParams.get("lotteryId");

    fetch("/lotteries/" + lotteryId)
        .then(resp => resp.json())
        .then(lotteryFromServer => {
            document.getElementById("title").value = lotteryFromServer.title;
            document.getElementById("participantsLimit").value = lotteryFromServer.participantsLimit;
        });
}

function updateLottery() {
    const title = document.getElementById("title").value;
    const participantsLimit = document.getElementById("participantsLimit").value;
    const lotteryId = new URL(window.location.href).searchParams.get("lotteryId");

    fetch("/lotteries/" + lotteryId, {
        method: "put",
        body: JSON.stringify({
            title: title,
            participantsLimit: participantsLimit
        }),
        headers: {
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then(resp => resp.json())
        .then(successful => {
            if (successful) {
                window.location.href = "/lotteries.html";
            } else {
                alert("Failed to update");
            }
        });
}