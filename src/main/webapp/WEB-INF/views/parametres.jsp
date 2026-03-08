<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.correction.model.Parametre" %>
<%@ page import="com.example.correction.model.Matiere" %>
<%@ page import="com.example.correction.model.Operateur" %>
<%@ page import="com.example.correction.model.Resolution" %>

<html>

<head>
	<title>Parametres</title>
	<link rel="stylesheet" href="/css/style.css">
</head>

<body>

	<div class="sidebar">

		<h2>ETU3510</h2>

		<a href="/final">Note finale</a>
		<a href="/notes">Notes</a>
		<a href="/parametres" class="active">Parametres</a>

	</div>


	<div class="main">

		<div class="container">

			<h1>Gestion des paramètres</h1>

			<%
				Parametre edit = (Parametre) request.getAttribute("paramEdit");
			%>

			<form action="/parametres/save" method="post">

				<% if(edit != null){ %>
					<input type="hidden" name="id" value="<%=edit.getId()%>">
				<% } %>


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

					<label>Operateur</label>

					<select name="operateur">

						<%
							List<Operateur> operateurs = (List<Operateur>) request.getAttribute("operateurs");

							if(operateurs != null){
								for(Operateur o : operateurs){

									boolean selected = edit != null && edit.getOperateur().getId().equals(o.getId());
						%>

						<option value="<%=o.getId()%>" <%= selected ? "selected" : "" %>>
							<%=o.getOperateur()%>
						</option>

						<%
								}
							}
						%>

					</select>

				</div>



				<div class="form-group">

					<label>Diff</label>

					<input
						type="number"
						step="0.1"
						name="diff"
						value="<%= edit != null ? edit.getDiff() : "" %>"
					>

				</div>



				<div class="form-group">

					<label>Resolution</label>

					<select name="resolution">

						<%
							List<Resolution> resolutions = (List<Resolution>) request.getAttribute("resolutions");

							if(resolutions != null){
								for(Resolution r : resolutions){

									boolean selected = edit != null && edit.getResolution().getId().equals(r.getId());
						%>

						<option value="<%=r.getId()%>" <%= selected ? "selected" : "" %>>
							<%=r.getNom()%>
						</option>

						<%
								}
							}
						%>

					</select>

				</div>


				<button>
					<%= edit != null ? "Modifier le parametre" : "Ajouter le parametre" %>
				</button>

			</form>



			<h2 style="margin-top:40px;">Liste des paramètres</h2>

			<table>

				<thead>

					<tr>
						<th>Matiere</th>
						<th>Operateur</th>
						<th>Diff</th>
						<th>Resolution</th>
						<th>Action</th>
					</tr>

				</thead>

				<tbody>

					<%
						List<Parametre> params = (List<Parametre>) request.getAttribute("parametres");

						if(params != null){
							for(Parametre p : params){
					%>

					<tr>

						<td><%= p.getMatiere().getNom() %></td>
						<td><%= p.getOperateur().getOperateur() %></td>
						<td><%= p.getDiff() %></td>
						<td><%= p.getResolution().getNom() %></td>

						<td class="table-actions">

							<a href="/parametres/edit?id=<%=p.getId()%>">
								Modifier
							</a>

							<a
								class="action-delete"
								href="/parametres/delete?id=<%=p.getId()%>"
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