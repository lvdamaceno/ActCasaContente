package br.com.sankhya.actcasacontente;

import java.math.BigDecimal;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.QueryExecutor;
import br.com.sankhya.extensions.actionbutton.Registro;

public class ActAlterarNsuLista implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao ctx) throws Exception {
		// TODO Auto-generated method stub

		Registro[] linhas = ctx.getLinhas();

		String paramNsu = (String) ctx.getParam("NSU");
		String paramAutorizacao = (String) ctx.getParam("AUTH");

		Integer paramNumnota = (Integer) ctx.getParam("NUMNOTA");
		BigDecimal paramNumnotaBigDecimal = new BigDecimal(paramNumnota);

		QueryExecutor query = ctx.getQuery();

		for (Registro linha : linhas) {
			BigDecimal numnota = (BigDecimal) linha.getCampo("NUMNOTA");

			if (paramNumnotaBigDecimal.compareTo(numnota) == 0) {
				query.setParam("NUFIN", linha.getCampo("NUFIN"));
				query.setParam("NSU", paramNsu);
				query.setParam("AUTH", paramAutorizacao);
				query.update("UPDATE TGFTEF SET AUTORIZACAO = {AUTH}, NUMNSU = {NSU} WHERE NUFIN = {NUFIN}");

			} else {
				ctx.mostraErro("Mais de uma nota selecionada ou Numero da Nota informado não encontrado.");
			}
		}
		ctx.setMensagemRetorno("Nsu/Autorização alterado com sucesso!");

	}

}