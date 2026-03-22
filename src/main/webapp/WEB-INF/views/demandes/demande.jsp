<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.forage.model.Demande" %>
<%@ page import="com.example.forage.model.Client" %>

<html>
    <head>
        <title>Demande</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>

    <body>

        <div class="sidebar">
            <h2>FORAGE</h2>
            <a href="/clients">Clients</a>
            <a href="/demandes" class="active">Demandes</a>
        </div>

        <div class="main">
        <div class="container">

        <h1>Gestion Demande</h1>

        <form method="post" action="/demandes">

            <div class="form-group">
                <label>Client</label>
                <select name="client">
                <%
                List<Client> clients = (List<Client>) request.getAttribute("clients");
                if(clients != null){
                    for(Client c : clients){
                        %>
                            <option value="<%=c.getId()%>"><%=c.getNom()%></option>
                        <%
                    }
                }
                %>
                </select>
            </div>

            <div class="form-group">
                <label>Lieu</label>
                <input type="text" name="lieu">
            </div>

            <div class="form-group">
                <label>District</label>
                <input type="text" name="district">
            </div>

            <button class="btn btn-primary">Ajouter</button>

        </form>

        <br>

        <table>
            <tr>
                <th>ID</th>
                <th>Client</th>
                <th>Lieu</th>
                <th>District</th>
                <th>Date</th>
                <th>Action</th>
            </tr>

            <%
            List<Demande> demandes = (List<Demande>) request.getAttribute("demandes");

            if(demandes != null){
                for(Demande d : demandes){
                    %>
                    <tr>
                        <td><%=d.getId()%></td>
                        <td><%=d.getClient().getNom()%></td>
                        <td><%=d.getLieu()%></td>
                        <td><%=d.getDistrict()%></td>
                        <td><%=d.getDate()%></td>
                        <td>
                            <a href="/demandes/edit?id=<%=d.getId()%>">Modifier</a> |
                            <a href="/demandes/delete?id=<%=d.getId()%>">Supprimer</a>
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