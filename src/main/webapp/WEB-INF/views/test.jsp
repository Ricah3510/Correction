<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.correction.model.Candidat" %>

<!DOCTYPE html>
<html>
<head>
    <title>Liste des candidats</title>
</head>
<body>

    <h2>Liste des candidats</h2>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nom</th>
        </tr>

        <%
            List<Candidat> listes = (List<Candidat>) request.getAttribute("listes");

            if(listes != null){
                for(Candidat c : listes){
        %>

        <tr>
            <td><%= c.getId() %></td>
            <td><%= c.getNom() %></td>
        </tr>

        <%
                }
            }
        %>

    </table>

</body>
</html>