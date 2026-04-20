<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.forage.model.*" %>

<%
    List<Demande> demandes = (List<Demande>) request.getAttribute("demandes");
    List<Type> types = (List<Type>) request.getAttribute("types");
%>

<html>
<head>
    <title>Devis</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>

    <div class="sidebar">
        <h2>FORAGE</h2>
        <a href="/clients">Clients</a>
        <a href="/demandes">Demandes</a>
        <a href="/devis" class="active">Devis</a>
        <a href="/devis/search">Voir Devis</a>
        <a href="/status">Status</a>
        <a href="/demandes/status">Demande Status</a>

    </div>

    <div class="main">
        <div class="container">
        <%
            String error = (String) request.getAttribute("error");
            if(error != null){
            %>
                <div style="color:red; font-weight:bold;">
                    <%= error %>
                </div>
            <%
            }
        %>
        <h1>Créer Devis</h1>

        <form method="post" action="/devis">


        <div class="form-group">

            <label>Demande</label>

            <select id="demandeSelect" name="demande" onfocus="loadDemandeDetails()" onchange="loadDemandeDetails();loadDevisIfExists();checkDevisStatus()">
                <option value="">Selectionner la demande</option>
                <%
                for(Demande d : demandes){
                    %>
                    <option value="<%=d.getId()%>">
                        Demande <%=d.getId()%>
                    </option>
                    <%
                }
                %>
            </select>

        </div>

        <div class="form-group">
            <label>Type</label>
            <select name="type" onchange="loadDevisIfExists(); checkDevisStatus()">
                <%
                for(Type t : types){
                    %>
                    <option value="<%=t.getId()%>"><%=t.getLibelle()%></option>
                    <%
                }
                %>
            </select>
        </div>

        <br>

        <div>
            <b>Client :</b> <span id="client"></span><br>
            <b>Lieu :</b> <span id="lieu"></span><br>
            <b>District :</b> <span id="district"></span>
        </div>

        <hr>

        <h2>Détails</h2>

        <table>
            <thead>
                <tr>
                    <th>Motif</th>
                    <th>Qte</th>
                    <th>PU</th>
                    <th>Montant</th>
                    <%-- <th>Action</th> --%>
                </tr>
            </thead>

            <tbody id="detailsBody">

                <tr>
                    <td><input type="text" name="libelle[]"></td>
                    <td><input type="number" step="0.01" class="qte" name="qte[]" oninput="calculMontant(this.parentElement.parentElement)"></td>
                    <td><input type="number" step="0.01" class="pu" name="pu[]" oninput="calculMontant(this.parentElement.parentElement)"></td>
                    <td class="montant">0.00</td>
                    <td>
                        <button type="button" onclick="removeRow(this)">X</button>
                    </td>
                </tr>

            </tbody>
        </table>

        <br>

        <button type="button" onclick="addRow()">+ Ajouter ligne</button>

        <h3>Total : <span id="total">0.00</span></h3>

        <br>

        <button class="btn btn-primary">Enregistrer</button>

        </form>

        </div>
    </div>

    <script src="/js/script-devis.js"></script>
</body>
</html>