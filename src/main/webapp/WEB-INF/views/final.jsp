<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.correction.model.Candidat" %>
<%@ page import="com.example.correction.model.Matiere" %>
<%@ page import="com.example.correction.model.NoteFinal" %>

<html>
<head>
    <title>Note Finale</title>
    <link rel="stylesheet" href="/css/style.css">

</head>

<body>

    <div class="sidebar">

        <h2>ETU3510</h2>

        <a href="/final" class="active">Note finale</a>
        <a href="/notes">Notes</a>
        <a href="/parametres">Parametres</a>

    </div>

    <div class="main">

    <div class="container">

        <h1 class ="etu"> ETU3510</h1>
        <h1>Calcul Note Finale</h1>

        <form method="post" action="/final">

            <div class="form-group">

            <label>Matiere</label>

                <select name="matiere">
                <%
                List<Matiere> matieres = (List<Matiere>) request.getAttribute("matieres");
                if(matieres != null){
                for(Matiere m : matieres){
                    %>
                    <option value="<%=m.getId()%>"><%=m.getNom()%></option>
                    <%
                }
                }
                %>

                </select>

            </div>


            <div class="form-group">

                <label>Candidat</label>

                <select name="candidat">

                <%
                List<Candidat> candidats = (List<Candidat>) request.getAttribute("candidats");

                if(candidats != null){
                for(Candidat c : candidats){
                    %>
                    <option value="<%=c.getId()%>"><%=c.getNom()%></option>
                    <%
                }
                }
                %>

                </select>

            </div>

            <button class="btn btn-primary">Calculer</button>

        </form>
    <%

    NoteFinal nf = (NoteFinal) request.getAttribute("noteFinal");

    if(nf != null){
        %>
        <br>
        <br>
        <div class="result-card">
            <div class="result-header">
                <h2>Note finale</h2>
                <div class="result-score">
                    <%= nf.getNote() %>
                </div>
            </div>

            <div class="result-info">

                <div class="result-item">
                    <span class="label">Candidat</span>
                    <span class="value"><%= nf.getCandidat().getNom() %></span>
                </div>

                <div class="result-item">
                    <span class="label">Matiere</span>
                    <span class="value"><%= nf.getMatiere().getNom() %></span>
                </div>

            </div>

        </div>
        <%
    }
    %>

    </div>
    </div>

</body>
</html>