$(document).ready(function() {
	$(".yes-modal").on("click", function(evt) {
		evt.preventDefault();
		$el = $(evt.target);
		// faz um get para remover o livro
		$.get("CadastroLivro?action=remove&id="+$el.data("id"), function(data, status){
			if (status == "success") {
				alert("livro deletado com sucesso");
				window.location.replace("http://localhost:8080/biblioteca/CadastroLivro?action=list");
			}
	    });
	});
});