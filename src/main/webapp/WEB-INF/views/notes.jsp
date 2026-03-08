<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.correction.model.Note" %>
<%@ page import="com.example.correction.model.Candidat" %>
<%@ page import="com.example.correction.model.Matiere" %>
<%@ page import="com.example.correction.model.Correcteur" %>

<html>

<head>
	<title>Notes</title>
	<link rel="stylesheet" href="/css/style.css">
</head>

<body>

	<div class="sidebar">

		<h2>ETU3510</h2>

		<a href="/final">Note finale</a>
		<a href="/notes" class="active">Notes</a>
		<a href="/parametres">Parametres</a>

	</div>


	<div class="main">

		<div class="container">

			<h1>Gestion des notes</h1>

			<%
				Note edit = (Note) request.getAttribute("noteEdit");
			%>

			<form action="/notes/save" method="post">

				<% if(edit != null){ %>
					<input type="hidden" name="id" value="<%=edit.getId()%>">
				<% } %>


				<div class="form-group">

					<label>Candidat</label>

					<select name="candidat">

						<%
							List<Candidat> candidats = (List<Candidat>) request.getAttribute("candidats");

							if(candidats != null){
								for(Candidat c : candidats){

									boolean selected = edit != null && edit.getCandidat().getId().equals(c.getId());
						%>

						<option value="<%=c.getId()%>" <%= selected ? "selected" : "" %>>
							<%=c.getNom()%>
						</option>

						<%
								}
							}
						%>

					</select>

				</div>



				<div class="form-group">

					<label>Matiere</label>

					<select name="matiere">

						<%
							List<Matiere> matieres = (List<Matiere>) request.getAttribute("matieres");

							if(matieres != null){
								for(Matiere m : matieres){

									boolean selected = edit != null && edit.getMatiere().getId().equals(m.getId());
						%>

						<option value="<%=m.getId()%>" <%= selected ? "selected" : "" %>>
							<%=m.getNom()%>
						</option>

						<%
								}
							}
						%>

					</select>

				</div>



				<div class="form-group">

					<label>Correcteur</label>

					<select name="correcteur">

						<%
							List<Correcteur> correcteurs = (List<Correcteur>) request.getAttribute("correcteurs");

							if(correcteurs != null){
								for(Correcteur c : correcteurs){

									boolean selected = edit != null && edit.getCorrecteur().getId().equals(c.getId());
						%>

						<option value="<%=c.getId()%>" <%= selected ? "selected" : "" %>>
							<%=c.getNom()%>
						</option>

						<%
								}
							}
						%>

					</select>

				</div>



				<div class="form-group">

					<label>Note</label>

					<input
						type="number"
						name="note"
						step="0.1"
						min="0"
						max="20"
						value="<%= edit != null ? edit.getNote() : "" %>"
					>

				</div>


				<button>
					<%= edit != null ? "Modifier la note" : "Ajouter la note" %>
				</button>

			</form>



			<h2 style="margin-top:40px;">Liste des notes</h2>

			<table>

				<thead>

					<tr>
						<th>Candidat</th>
						<th>Matiere</th>
						<th>Correcteur</th>
						<th>Note</th>
						<th>Action</th>
					</tr>

				</thead>

				<tbody>

					<%
						List<Note> notes = (List<Note>) request.getAttribute("notes");

						if(notes != null){
							for(Note n : notes){
					%>

					<tr>

						<td><%= n.getCandidat().getNom() %></td>
						<td><%= n.getMatiere().getNom() %></td>
						<td><%= n.getCorrecteur().getNom() %></td>
						<td><%= n.getNote() %></td>

						<td class="table-actions">

							<a href="/notes/edit?id=<%=n.getId()%>">
								Modifier
							</a>

							<a
								class="action-delete"
								href="/notes/delete?id=<%=n.getId()%>"
							>
								Supprimer
							</a>

						</td>

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