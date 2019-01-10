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
        <td>${lottery.status}</td>
        <td>${lottery.startDate}</td>
        <td>${lottery.endDate}</td>
        <td>${lottery.assigned_participant_id}</td>
    `;
    document.getElementById("table-body").appendChild(tr);
}

// <td>${lottery.participant ? lottery.participant.email + " " + lottery.participant.code: ""}</td>
function saveCredentials() {

}

function createLottery() {
    const title = document.getElementById("title").value;
    const participantsLimit = document.getElementById("participantsLimit").value;
    fetch("/lotteries", {
        method: "post",
        body: JSON.stringify({
            title: title,
            participantsLimit: participantsLimit,
            status:'default'
        }),
        headers:{
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then(()=>{
        window.location.href = "/lotteries.html"
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