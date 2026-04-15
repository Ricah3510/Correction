function loadStatus() {

    let demande = document.querySelector("select[name='demande']").value;

    if (!demande) {
        document.getElementById("currentStatus").innerHTML = "";
        document.getElementById("historyBody").innerHTML = "";
        return;
    }

    fetch(`/api/demande/status?demande=${demande}`)
        .then(res => {
            if (!res.ok) throw new Error("Erreur API");
            return res.json();
        })
        .then(data => {

            let currentDiv = document.getElementById("currentStatus");

            if (data.current) {
                currentDiv.innerHTML = `
                    <b>${data.current.status}</b><br>
                    ${data.current.observation || ""}
                `;
            } else {
                currentDiv.innerHTML = "Aucun status";
            }

            let body = document.getElementById("historyBody");
            body.innerHTML = "";

            if (!data.history) return;

            data.history.forEach(s => {

                let row = document.createElement("tr");

                row.innerHTML = `
                    <td>${s.status || ""}</td>
                    <td>
                        <input
                            type="text"
                            value="${s.observation || ""}"
                            onblur="updateObservation(${s.id}, this.value)"
                        >
                    </td>
                    <td>${s.date}</td>
                `;

                body.appendChild(row);
            });

        })
        .catch(err => {
            console.error(err);
        });
}

function updateObservation(id, value) {

    fetch("/api/demande/status/update-observation", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: `id=${id}&observation=${encodeURIComponent(value)}`
    })
    .then(res => res.text())
    .then(res => {
        console.log("updated:", res);
        alert("UpdateObservation OK");
    })
    .catch(err => console.error(err));
}