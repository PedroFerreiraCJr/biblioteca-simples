$(function(){
	$(".autor_add").on("click", function(){
		$(".autores")
		.append('<div class="input-group">\
		          <input name="autores" type="text" class="form-control" placeholder="Autores" title="Autores do livro">\
				  <span class="input-group-addon autor_remove">\
   					<a><span class="glyphicon glyphicon-minus"></span></a>\
				  </span>\
				</div>');
		autor_remove();
	});
	
	function autor_remove() {
		$(".autor_remove").on("click", function(evt){
			$el = $(evt.target.parentNode.parentNode.parentNode);
			
			if (!$el.hasClass("unremovable")) {
				$el.remove();
			}
		});
	}
	
	$(".revisor_add").on("click", function(){
		$(".revisores")
		.append('<div class="input-group">\
		          <input name="autores" type="text" class="form-control" placeholder="Revisores" title="Revisores do livro">\
				  <span class="input-group-addon revisor_remove">\
   					<a><span class="glyphicon glyphicon-minus"></span></a>\
				  </span>\
				</div>');
		revisor_remove();
	});
	
	function revisor_remove() {
		$(".revisor_remove").on("click", function(evt){
			$el = $(evt.target.parentNode.parentNode.parentNode);
			
			if (!$el.hasClass("unremovable")) {
				$el.remove();
			}
		});
	}
	
	autor_remove();
	revisor_remove();
});