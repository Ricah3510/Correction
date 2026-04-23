<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.forage.model.*" %>

<%
    List<Demande> demandes = (List<Demande>) request.getAttribute("demandes");
    List<Status> statuses = (List<Status>) request.getAttribute("statuses");
    String selectedDemande = request.getParameter("demande");
%>

<html>
<head>
    <title>Demande Status</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>

<div class="sidebar">
    <h2>FORAGE</h2>
    <a href="/dashboard">Dashboard</a>
    <a href="/clients">Clients</a>
    <a href="/demandes">Demandes</a>
    <a href="/devis">Devis</a>
    <a href="/devis/search">Voir Devis</a>
    <a href="/status">Status</a>
    <a href="/demandes/status" class="active">Demande Status</a>
</div>

<div class="main">
<div class="container">

<h1>Ajouter Status à une Demande</h1>

<form method="post" action="/demandes/status">

    <div class="form-group">
        <label>Demande</label>
        <select name="demande" onchange="loadStatus()">
            <option value="">Selectionner la demande</option>
            <%
            for(Demande d : demandes){
            %>
                <%-- <option value="<%=d.getId()%>">
                    Demande <%=d.getId()%> - <%=d.getLieu()%>
                </option> --%>
                <option value="<%=d.getId()%>"
                    <%= (selectedDemande != null && selectedDemande.equals(String.valueOf(d.getId()))) ? "selected" : "" %>>
                    
                    Demande <%=d.getId()%> - <%=d.getLieu()%>
                </option>
            <%
            }
            %>
        </select>
    </div>

    <div class="form-group">
        <label>Status</label>
        <select name="status">
            <%
            for(Status s : statuses){
            %>
                <option value="<%=s.getId()%>">
                    <%=s.getLibelle()%>
                </option>
            <%
            }
            %>
        </select>
    </div>

    <div class="form-group">
        <label>Observation</label>
        <input type="text" name="observation">
    </div>

    <button class="btn btn-primary">Ajouter</button>

</form>

<hr>

<h2>Status actuel</h2>
<div id="currentStatus"></div>

<h2>Historique</h2>

<table>
    <thead>
        <tr>
            <th>Status</th>
            <th>Observation</th>
            <th>Date</th>
        </tr>
    </thead>
    <tbody id="historyBody"></tbody>
</table>

</div>
</div>

<script src="/js/script-demande-status.js"></script>

</body>
</html>
<script>
window.onload = function(){

    const select = document.querySelector("select[name='demande']");

    if(select && select.value){
        loadStatus();
    }
}
</script>