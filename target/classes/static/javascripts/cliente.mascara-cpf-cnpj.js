var Brewer = Brewer || {};
Brewer.MascaraCpfCnpj = (function() {
	function MascaraCpfCnpj() {
		this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
		this.inputCpfCnpj = $('#cpf-cnpj');
		this.labelTipoPessoa = $('[for=cpf-cnpj]');
		
	}

	MascaraCpfCnpj.prototype.enable = function() {
		this.radioTipoPessoa.on('change', onTipoPessoaAlterado.bind(this));
	}
	function onTipoPessoaAlterado(event) {
		var tipoPessoaSelecionada = $(event.currentTarget);
		this.inputCpfCnpj.removeAttr('disabled');
		this.labelTipoPessoa.text(tipoPessoaSelecionada.data('documento'));
		this.inputCpfCnpj.mask(tipoPessoaSelecionada.data('mascara'));
		this.inputCpfCnpj.val('');
		this.inputCpfCnpj.focus();
		

	}
	return MascaraCpfCnpj;
}());

$(function() {
	var mascaraCpfCnpj = new Brewer.MascaraCpfCnpj();
	mascaraCpfCnpj.enable();

});