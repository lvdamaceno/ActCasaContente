package br.com.sankhya.actcasacontente;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.QueryExecutor;
import br.com.sankhya.extensions.actionbutton.Registro;

public class ActAlterarNsuLista implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao ctx) throws Exception {
		// TODO Auto-generated method stub

		Registro[] linhas = ctx.getLinhas();

		String nsu = (String) ctx.getParam("NSU");
		String autorizacao = (String) ctx.getParam("AUTH");

		QueryExecutor query = ctx.getQuery();

		for (Registro linha : linhas) {
			query.setParam("NUFIN", linha.getCampo("NUFIN"));
			query.setParam("NSU", nsu);
			query.setParam("AUTH", autorizacao);
			query.update("UPDATE TGFTEF SET AUTORIZACAO = {AUTH}, NUMNSU = {NSU} WHERE NUFIN = {NUFIN}");

		}

		ctx.setMensagemRetorno("Nsu/Autorização alterado com sucesso!");

	}

}