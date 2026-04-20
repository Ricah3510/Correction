function showDemandeInfo() {
    let select = document.getElementById("demandeSelect");
    let selected = select.options[select.selectedIndex];

    document.getElementById("client").innerText = selected.getAttribute("data-client");
    document.getElementById("lieu").innerText = selected.getAttribute("data-lieu");
    document.getElementById("district").innerText = selected.getAttribute("data-district");
}

function calculMontant(row) {
    alea1(row);
    let qte = row.querySelector(".qte").value || 0;
    let pu = row.querySelector(".pu").value || 0;

    let montant = qte * pu;

    row.querySelector(".montant").innerText = montant.toFixed(2);

    calculTotal();
}

function alea1(row) {
    let pu = row.querySelector(".pu").value || 0;
    console.log("pu" + pu);
    if (pu >= 1000000) {
        console.log("pu + 1M");
        let nouveaupu = pu - ( (pu*10) /100);
        row.querySelector(".pu").value = nouveaupu;
    }
}

function calculTotal() {
    let total = 0;

    document.querySelectorAll(".montant").forEach(el => {
        total += parseFloat(el.innerText) || 0;
    });

    document.getElementById("total").innerText = total.toFixed(2);
}

function addRow() {
    let table = document.getElementById("detailsBody");

    let row = document.createElement("tr");

    row.innerHTML = `
        <input type="hidden" name="detailId[]" value="">
        <td><input type="text" name="libelle[]"></td>
        <td><input type="number" step="0.01" class="qte" name="qte[]" oninput="calculMontant(this.parentElement.parentElement)"></td>
        <td><input type="number" step="0.01" class="pu" name="pu[]" oninput="calculMontant(this.parentElement.parentElement)"></td>
        <td class="montant">0.00</td>
        <td>
            <button type="button" onclick="removeRow(this)">X</button>
        </td>
    `;

    table.appendChild(row);
}

function removeRow(button) {

    let table = document.getElementById("detailsBody");

    if (table.rows.length <= 1) {
        alert("Au moins une ligne est requise");
        return;
    }

    let row = button.parentElement.parentElement;
    row.remove();

    calculTotal();
}
function loadDemandeDetails() {

    let id = document.getElementById("demandeSelect").value;

    fetch(`/api/demande/${id}`)
        .then(res => res.json())
        .then(data => {

            document.getElementById("client").innerText = data.client.nom;
            document.getElementById("lieu").innerText = data.lieu;
            document.getElementById("district").innerText = data.district;

        })
        .catch(err => console.error(err));
}

function loadDevisIfExists() {

    let demande = document.getElementById("demandeSelect").value;
    let type = document.querySelector("select[name='type']").value;
    fetch(`/api/devis?demande=${demande}&type=${type}`)
    .then(res => {

        if (!res.ok) {
            return null;
        }

        return res.json();
    })
    .then(data => {

        let table = document.getElementById("detailsBody");
        table.innerHTML = "";

        if (!data || !data.detailsDeviss || data.detailsDeviss.length === 0) {
            addRow();
            return;
        }

        data.detailsDeviss.forEach(d => {
            let row = document.createElement("tr");

            row.innerHTML = `
                <input type="hidden" name="detailId[]" value="${d.id}">
                <td><input type="text" name="libelle[]" value="${d.libelle}"></td>
                <td><input type="number" class="qte" name="qte[]" value="${d.qte}" oninput="calculMontant(this.parentElement.parentElement)"></td>
                <td><input type="number" class="pu" name="pu[]" value="${d.pu}" oninput="calculMontant(this.parentElement.parentElement)"></td>
                <td class="montant">${(d.qte * d.pu).toFixed(2)}</td>
                <td><button type="button" onclick="removeRow(this)">X</button></td>
            `;

            table.appendChild(row);
        });

    })
    .catch(err => {
        console.error("Erreur AJAX :", err);
        addRow();
    });
}


function checkDevisStatus() {

    let demande = document.getElementById("demandeSelect").value;
    let type = document.querySelector("select[name='type']").value;

    fetch(`/api/devis/status?demande=${demande}&type=${type}`)
        .then(res => res.json())
        .then(data => {

            if (data.isAccepted) {

                document.querySelectorAll("input, button").forEach(el => {
                    el.disabled = true;
                });

                let msg = document.getElementById("statusMsg");

                if (!msg) {
                    msg = document.createElement("div");
                    msg.id = "statusMsg";
                    msg.style.color = "red";
                    msg.style.fontWeight = "bold";
                    msg.innerText = "Ce devis est accepté — modification interdite";

                    document.querySelector(".container").prepend(msg);
                }

            }else {
                document.querySelectorAll("input, button").forEach(el => {
                    el.disabled = false;
                });
            
                let msg = document.getElementById("statusMsg");
                if (msg) msg.remove();
            }

        })
        .catch(err => console.error(err));
}