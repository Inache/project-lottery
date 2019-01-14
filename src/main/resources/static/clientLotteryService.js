function loadLotteries() {
    fetch("/lotteries", {
        method: "get"
    }).then(resp => resp.json()
    ).then(lotteries => {
        for (const lottery of lotteries) {
            addLottery(lottery);
        }
    });
}

function addLottery(lottery) {
    const tr = document.createElement("tr");
    tr.innerHTML = `
        <td>${lottery.id}</td>
        <td>${lottery.title}</td>
        <td>
       
        <button class="btn btn-primary" onclick="openRegistrationWindow(${lottery.id})">Register To Lottery</button>
                <button class="btn btn-primary" onclick="checkParticipantStatusWindow(${lottery.id})">Check status</button>

        </td>
    `;
    document.getElementById("table-body").appendChild(tr);
}

function openRegistrationWindow(id) {
    window.location.href = "/registeringParticipant.html?lotteryId=" + id;
}

function checkParticipantStatusWindow(id) {
    window.location.href = "/status.html?lotteryId=" + id;
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
        headers: {
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then((resp) => resp.json())
        .then(response => {
            if (response.status == "OK") {
                alert("You have been succesfully registered.");
                window.location.href = "/availableLotteries.html";
            }
            else {
                alert("Cant register" + response.reason);
                window.location.reload();
            }
        })
}

function checkParticipantsStatus() {
    const lotteryId = new URL(window.location.href).searchParams.get("lotteryId");
    const email = document.getElementById("email").value;
    const code = document.getElementById("code").value;

    fetch("/status?id=" + lotteryId + "&email=" + email + "&code=" + code, {
        method: "get",
        headers: {
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then((resp) => resp.json())
        .then(response => {
            if (response.status == "WIN") {
                alert("WIN");
            } else if (response.status == "LOOSE") {
                alert("LOOSE");
            } else if (response.status == "PENDING") {
                alert("PENDING");
            } else {
                alert("ERROR");
            }
        })

}