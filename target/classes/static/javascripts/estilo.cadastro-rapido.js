/*criar um objeto Brewer ou um novo objeto*/
var Brewer = Brewer || {};

/* criando a função principal e seu construtor */
Brewer.EstiloCadastroRapido = (function() {

	/* construtor */
	function EstiloCadastroRapido() {

		/*
		 * colocamos no construtor todas as iniciliazações, não com var mas com
		 * this
		 */
		this.modal = $('#modalCadastroRapidoEstilo');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-estilo-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeEstilo = $('#nomeEstilo');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');
	}
	EstiloCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) {
			event.preventDefault()
		});
		/* modal aberto chama o método - onModalShow */
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		/* modal fechado chama o método - onModalClose */
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		/* clicando no botão salvar, chama o método - onBotaoSalvarClick */
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}

	function onModalShow() {
		this.inputNomeEstilo.focus();
	}

	function onModalClose() {
		this.inputNomeEstilo.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}

	function onBotaoSalvarClick() {
		var nomeEstilo = this.inputNomeEstilo.val().trim();
		$.ajax({
			url : this.url,
			method : 'POST',
			contentType : 'application/json',
			data : JSON.stringify({
				nome : nomeEstilo
			}),
			error : onErrorSalvandoEstilo.bind(this),
			success : onSuccessSalvandoEstilo.bind(this)
		});
	}

	function onErrorSalvandoEstilo(obj) {
		var mensagem = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagem + '</span>');
		this.form.find('.form-group').addClass('has-Error');
	}
	function onSuccessSalvandoEstilo(estilo) {
		var comboEstilo = $('#estilo');
		comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome + '</option>');
		comboEstilo.val(estilo.codigo);
		this.modal.modal('hide');

	}

	return EstiloCadastroRapido;

}());

$(function() {
	var estiloCadastroRapido = new Brewer.EstiloCadastroRapido();
	estiloCadastroRapido.iniciar();
});
