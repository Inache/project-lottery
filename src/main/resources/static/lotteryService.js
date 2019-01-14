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
        <button class="btn btn-primary" onclick="chooseWinner(${lottery.id})">Choose Winner</button>
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
    }).then((resp) => resp.json())
        .then(response => {
            if (response.status == "OK") {
                window.location.reload();
            }
            else {
                alert("Cant close lottery." + response.reason);
            }
        })
}

function chooseWinner(id) {
    fetch("/choose-winner",{
        method: "post",
        body: JSON.stringify({
            id: id
        }),
        headers:{
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then((resp)=>resp.json())
        .then(response=>{
            if (response.status == "OK") {
                window.location.reload();
            }
            else {
                alert("Cant chose winner." + response.reason);
            }
        });
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
    }).then((resp)=>resp.json())
        .then(response=>{
            if (response.status == "OK") {
                window.location.href="/adminPage.html";
            }
            else {
                alert("Cant create lottery." + response.reason);
            }
        });
}
