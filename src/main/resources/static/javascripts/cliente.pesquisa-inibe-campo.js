var Brewer = Brewer || {};

Brewer.InibeCampoPesquisa = (function() {

	function InibeCampoPesquisa() {
		this.inputCliente = $('#cliente');
		this.inputCpfOuCnpj = $('#cpfOuCnpj');
	}

	InibeCampoPesquisa.prototype.iniciar = function() {
		if (this.inputCliente.attr('autofocus')) {
			this.inputCpfOuCnpj.val('');
		}

	}

	return InibeCampoPesquisa;
}());

$(function() {
	var inibeCampoPesquisa = new Brewer.InibeCampoPesquisa();
	inibeCampoPesquisa.iniciar();
});