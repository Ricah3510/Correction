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

<div class="container">

    <h1>Calcul Note Finale</h1>
    <p class="etu">ETU3510</p>

    <form method="post" action="/final">

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

        <button type="submit">Calculer</button>

    </form>


    <%
        NoteFinal nf = (NoteFinal) request.getAttribute("noteFinal");

        if(nf != null){
    %>

        <div class="result">

            <h2>Note Finale</h2>

            <p>
                <strong>Candidat :</strong>
                <%= nf.getCandidat().getNom() %>
            </p>

            <p>
                <strong>Matiere :</strong>
                <%= nf.getMatiere().getNom() %>
            </p>

            <p class="note">
                <%= nf.getNote() %>
            </p>

        </div>

    <%
        }
    %>

</div>

</body>
</html>