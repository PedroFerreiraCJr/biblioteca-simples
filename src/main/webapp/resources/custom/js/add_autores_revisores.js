$(function(){
	$(".autor_add").on("click", function(){
		$(".autores")
		.append('<div>\
		          <input id="autores" name="autores" type="text" class="form-control autor-removable" placeholder="Autores" title="Autores do livro">\
	          </div>');
	});
	
	$(".autor_remove").on("click", function(){
		$(".autor-removable:last").remove();
	});
	
	$(".revisor_add").on("click", function(){
		$(".revisores")
		.append('<div>\
				<input id="revisores" name="revisores" type="text" class="form-control revisor-removable" placeholder="Revisores" title="Revisores do livro">\
	          </div>');
	});
	
	$(".revisor_remove").on("click", function(){
		$(".revisor-removable:last").remove();
	});
});