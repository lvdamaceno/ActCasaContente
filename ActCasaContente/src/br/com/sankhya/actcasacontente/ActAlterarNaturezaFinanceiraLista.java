package br.com.sankhya.actcasacontente;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import java.math.BigDecimal;

public class ActAlterarNaturezaFinanceiraLista implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao ctx) throws Exception {
		// TODO Auto-generated method stub

		Registro[] linhas = ctx.getLinhas();

		BigDecimal codNat = null;

		String temp = (String) ctx.getParam("CODNAT");

		codNat = new BigDecimal(temp);

		for (Registro linha : linhas)
		{
			linha.setCampo("CODNAT", codNat);
			linha.save();
		}

		ctx.setMensagemRetorno("Natureza alterada com sucesso!");

	}


}