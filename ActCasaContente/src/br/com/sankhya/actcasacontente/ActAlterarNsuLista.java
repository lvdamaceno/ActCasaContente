package br.com.sankhya.actcasacontente;

import java.math.BigDecimal;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.QueryExecutor;
import br.com.sankhya.extensions.actionbutton.Registro;

public class ActAlterarNsuLista implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao ctx) throws Exception {
	    Registro[] linhas = ctx.getLinhas();
	    
	    String paramNsu = getStringParam(ctx, "NSU");
	    String paramAutorizacao = getStringParam(ctx, "AUTH");
	    BigDecimal paramNumnota = getBigDecimalParam(ctx, "NUMNOTA");

	    if (linhas == null || linhas.length == 0) {
	        ctx.mostraErro("Nenhuma linha encontrada.");
	        return;
	    }

	    QueryExecutor query = ctx.getQuery();

	    for (Registro linha : linhas) {
	        BigDecimal numnota = (BigDecimal) linha.getCampo("NUMNOTA");

	        if (paramNumnota.compareTo(numnota) == 0) {
	            deleteExistingRecords(query, linha);
	            insertNewRecord(query, linha, paramNsu, paramAutorizacao);
	        } else {
	            ctx.mostraErro("Mais de uma nota selecionada ou Número da Nota informado não encontrado.");
	            return;
	        }
	    }
	    ctx.setMensagemRetorno("Nsu/Autorização alterado com sucesso!");
	}

	private String getStringParam(ContextoAcao ctx, String paramName) {
	    return (String) ctx.getParam(paramName);
	}

	private BigDecimal getBigDecimalParam(ContextoAcao ctx, String paramName) {
	    Integer paramValue = (Integer) ctx.getParam(paramName);
	    return new BigDecimal(paramValue);
	}

	private void deleteExistingRecords(QueryExecutor query, Registro linha) throws Exception {
	    query.setParam("NUFIN", linha.getCampo("NUFIN"));
	    query.update("DELETE FROM TGFTEF WHERE NUFIN = {NUFIN}");
	}

	private void insertNewRecord(QueryExecutor query, Registro linha, String paramNsu, String paramAutorizacao) throws Exception {
	    query.setParam("NUFIN", linha.getCampo("NUFIN"));
	    query.setParam("NSU", paramNsu);
	    query.setParam("AUTH", paramAutorizacao);

	    query.update(
	        "INSERT TGFTEF (TIPODOC, NUMCV, NUMDOC, NUMNSU, NUMPV, AUTORIZACAO, DESDOBRAMENTO, DTTRANSACAO, VLRTRANSACAO, VLRTAXA, NUFIN) " +
	        "VALUES (158, 000000000000000, 000000000000000, FORMAT(CAST({NSU} AS INT), '000000000000000'), 000000000000000, " +
	        "FORMAT(CAST({AUTH} AS INT), '000000000000000'), (SELECT DESDOBRAMENTO FROM TGFFIN WHERE NUFIN = {NUFIN}), " +
	        "(SELECT DTNEG FROM TGFFIN WHERE NUFIN = {NUFIN}), (SELECT VLRDESDOB FROM TGFFIN WHERE NUFIN = {NUFIN}), 7.12, {NUFIN})"
	    );
	}

}