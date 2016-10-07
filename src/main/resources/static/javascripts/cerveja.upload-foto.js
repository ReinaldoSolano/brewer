/*criar um objeto Brewer ou um novo objeto*/
var Brewer = Brewer || {};

/* criando a função principal e seu construtor */
Brewer.UploadFoto = (function() {

	/* construtor */
	function UploadFoto() {

		/*
		 * colocamos no construtor todas as iniciliazações, não com var mas com
		 * this
		 */
		this.inputNomeFoto = $('input[name=foto]');
		this.inputContentType = $('input[name=contentType]');

		this.htmlFotoCervejaTemplate = $('#foto-cerveja').html();
		this.template = Handlebars.compile(this.htmlFotoCervejaTemplate);
		//this.htmlFotoCerveja = template({nomeFoto : resposta.nome});
		this.containerFotoCerveja = $('.js-container-foto-cerveja');
		this.uploadDrop = $('#upload-drop');

	}
	UploadFoto.prototype.iniciar = function() {
		
		var settings = {
				type : 'json',
				filelimit : 1,
				allow : '*.(jpg|jpeg|png)',
				action : this.containerFotoCerveja.data('url-fotos'),
				complete : onUploadCompleto.bind(this),
				beforeSend : adicionarCsrfToken
		}
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
		
		if(this.inputNomeFoto.val()){
			onUploadCompleto.call(this,{nome : this.inputNomeFoto.val(), contentType : this.inputContentType.val()});
		}

	}
	function onUploadCompleto(resposta){
		this.inputNomeFoto.val(resposta.nome);
		this.inputContentType.val(resposta.contentType);
		
		this.uploadDrop.addClass('hidden');
		var htmlFotoCerveja = this.template({nomeFoto : resposta.nome});

		this.containerFotoCerveja.append(htmlFotoCerveja);
		
		$('.js-remove-foto').on('click', onRemoveFoto.bind(this));
	}
	
	function onRemoveFoto(){
		$('.js-foto-cerveja').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNomeFoto.val('');
		this.inputContentType.val('');
	}
	
	function adicionarCsrfToken(xhr) {
		var token = $('input[name=_csrf').val();
		var header = $('input[name=_csrf_header').val();
		xhr.setRequestHeader(header,token);
	}
	return UploadFoto;

}());
/* executa a funcao acima */
$(function() {
	var uploadFoto = new Brewer.UploadFoto();
	uploadFoto.iniciar();
});
