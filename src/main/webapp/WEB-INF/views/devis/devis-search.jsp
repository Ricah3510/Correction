<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.forage.model.*" %>

<%
List<Demande> demandes = (List<Demande>) request.getAttribute("demandes");
List<Type> types = (List<Type>) request.getAttribute("types");
List<Devis> devisList = (List<Devis>) request.getAttribute("devisList");
Map<Integer, String> statusMap = (Map<Integer, String>) request.getAttribute("statusMap");
%>

<html>
<head>
    <title>Recherche Devis</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>

<div class="sidebar">
    <h2>FORAGE</h2>
    <a href="/dashboard">Dashboard</a>
    <a href="/clients">Clients</a>
    <a href="/demandes">Demandes</a>
    <a href="/devis">Devis</a>
    <a href="/devis/search" class="active">Voir Devis</a>
    <a href="/status">Status</a>
    <a href="/demandes/status">Demande Status</a>

</div>

<div class="main">
<div class="container">

<h1>Recherche Devis</h1>

<form method="post" action="/devis/search">

    <div class="form-group">
        <label>Demande</label>
        <select name="demande">
        <% for(Demande d : demandes){ %>
            <option value="<%=d.getId()%>">Demande <%=d.getId()%></option>
        <% } %>
        </select>
    </div>

    <div class="form-group">
        <label>Type</label>
        <select name="type">
        <% for(Type t : types){ %>
            <option value="<%=t.getId()%>"><%=t.getLibelle()%></option>
        <% } %>
        </select>
    </div>

    <button class="btn btn-primary">Rechercher</button>

</form>

<hr>

<%
if(devisList != null && !devisList.isEmpty()){
%>

<%
for(Devis d : devisList){
    
%>

<div style="border:1px solid #ccc; padding:15px; margin-bottom:20px;">

    <h3>Devis #<%=d.getId()%></h3>

    <p>
        <b>Date :</b> <%=d.getDate()%><br>
        <b>Type :</b> <%=d.getType().getLibelle()%><br>
        <b>Total :</b> <%=d.getMontantTotal()%>
    </p>
<p>
    <b>Status :</b>
    <%= statusMap.get(d.getId()) %>
</p>
<%
String status = statusMap.get(d.getId());
boolean isAccepted = status != null && status.toLowerCase().contains("acceptee");
%>
<% if(isAccepted){ %>
    <%-- <div style="color:red; font-weight:bold;">
        Ce devis est accepté — modification interdite
    </div> --%>
<% } %>

    <table>
        <tr>
            <th>Motif</th>
            <th>Qte</th>
            <th>PU</th>
            <th>Montant</th>
        </tr>

        <%
        if(d.getDetailsDeviss() != null){
            for(DetailsDevis det : d.getDetailsDeviss()){
        %>
        <tr>
            <td><%=det.getLibelle()%></td>
            <td><%=det.getQte()%></td>
            <td><%=det.getPu()%></td>
            <td><%=det.getQte().multiply(det.getPu())%></td>
        </tr>
        <%
            }
        if(!isAccepted){ %>

            <%-- <form method="post" action="/devis/status" style="display:inline;">
                <input type="hidden" name="devisId" value="<%=d.getId()%>">
                <input type="hidden" name="action" value="accept">
                <button style="background-color:green; color:white;">Accepter</button>
            </form>

            <form method="post" action="/devis/status" style="display:inline;">
                <input type="hidden" name="devisId" value="<%=d.getId()%>">
                <input type="hidden" name="action" value="refuse">
                <button style="background-color:red; color:white;">Refuser</button>
            </form> --%>

        <% }
        }
        %>

    </table>

</div>

<%
}
%>

<%
}else if(devisList != null){
%>
<p>Aucun devis trouvé</p>
<%
}
%>

</div>
</div>

</body>
</html>