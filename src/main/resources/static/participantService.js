function loadParticipants() {
    fetch("/participants", {
        method: "get"
    }).then(
        resp => resp.json()
    ).then(participants => {
        for (const participant of participants) {
            addParticipant(participant);
        }
    });
}

function addParticipant(participant) {
    const tr = document.createElement("tr");
    tr.innerHTML = `
        <td>${participant.id}</td>
        <td>${participant.email}</td>
        <td>${participant.age}</td>
    `;
    document.getElementById("table-body").appendChild(tr);
}

function createParticipant() {
    const email = document.getElementById("email").value;
    const age = document.getElementById("age").value;

    fetch("/participants", {
        method: "post",
        body: JSON.stringify({
            email: email,
            age: age,
        }),
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
        }
    }).then(() => {
        window.location.href = "/participants.html";
    });
}