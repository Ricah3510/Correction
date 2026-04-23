<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.forage.model.Client" %>

<html>
    <head>
        <title>Client</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>

    <body>

        <div class="sidebar">
            <h2>FORAGE</h2>
            <a href="/dashboard">Dashboard</a>
            <a href="/clients" class="active">Clients</a>
            <a href="/demandes">Demandes</a>
            <a href="/devis">Devis</a>
            <a href="/devis/search">Voir Devis</a>
            <a href="/status">Status</a>
            <a href="/demandes/status">Demande Status</a>
        </div>

        <div class="main">
        <div class="container">

        <h1>Gestion Client</h1>

        <form method="post" action="/clients">

            <div class="form-group">
                <label>Nom</label>
                <input type="text" name="nom" required>
            </div>

            <div class="form-group">
                <label>Contact</label>
                <input type="text" name="contact">
            </div>

            <button class="btn btn-primary">Ajouter</button>

        </form>

        <br>

        <table>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Contact</th>
                <th>Action</th>
            </tr>

            <%
            List<Client> clients = (List<Client>) request.getAttribute("clients");

            if(clients != null){
                for(Client c : clients){
                    %>
                    <tr onclick="window.location='/clients/<%=c.getId()%>'" style="cursor:pointer;">
                        <td><%=c.getId()%></td>
                        <td><%=c.getNom()%></td>
                        <td><%=c.getContact()%></td>
                        <td>
                            <a href="/clients/edit?id=<%=c.getId()%>">Modifier</a> |
                            <a href="/clients/delete?id=<%=c.getId()%>">Supprimer</a>
                        </td>
                    </tr>
                    <%
                }
            }
            %>

        </table>

        </div>
        </div>

    </body>
</html>