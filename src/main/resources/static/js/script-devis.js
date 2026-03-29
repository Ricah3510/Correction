function showDemandeInfo() {
    let select = document.getElementById("demandeSelect");
    let selected = select.options[select.selectedIndex];

    document.getElementById("client").innerText = selected.getAttribute("data-client");
    document.getElementById("lieu").innerText = selected.getAttribute("data-lieu");
    document.getElementById("district").innerText = selected.getAttribute("data-district");
}

function calculMontant(row) {
    let qte = row.querySelector(".qte").value || 0;
    let pu = row.querySelector(".pu").value || 0;

    let montant = qte * pu;

    row.querySelector(".montant").innerText = montant.toFixed(2);

    calculTotal();
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
        <td><input type="text" name="libelle[]"></td>
        <td><input type="number" step="0.01" class="qte" name="qte[]" oninput="calculMontant(this.parentElement.parentElement)"></td>
        <td><input type="number" step="0.01" class="pu" name="pu[]" oninput="calculMontant(this.parentElement.parentElement)"></td>
        <td class="montant">0.00</td>
    `;

    table.appendChild(row);
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