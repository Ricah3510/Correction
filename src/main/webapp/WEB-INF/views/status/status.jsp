<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.forage.model.Status" %>

<%
    List<Status> statuses = (List<Status>) request.getAttribute("statuses");
%>

<html>
<head>
    <title>Status</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>

    <div class="sidebar">
        <h2>FORAGE</h2>

        <a href="/clients">Clients</a>
        <a href="/demandes">Demandes</a>
        <a href="/devis">Devis</a>
        <a href="/devis/search">Voir Devis</a>
        <a href="/status" class="active">Status</a>
        <a href="/demandes/status">Demande Status</a>
    </div>

    <div class="main">
        <div class="container">

            <h1>Gestion des Status</h1>

            <form method="post" action="/status">

                <div class="form-group">
                    <label>Libellé</label>
                    <input type="text" name="libelle" required>
                </div>

                <button class="btn btn-primary">Ajouter</button>

            </form>

            <br><br>

            <table>

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Libellé</th>
                    </tr>
                </thead>

                <tbody>

                <%
                if(statuses != null){
                    for(Status s : statuses){
                %>

                    <tr>
                        <td><%= s.getId() %></td>
                        <td><%= s.getLibelle() %></td>
                    </tr>

                <%
                    }
                }
                %>

                </tbody>

            </table>

        </div>
    </div>

</body>
</html>