package br.com.sankhya.actcasacontente;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
//import java.math.BigDecimal;

public class ActAlterarTaxaAdministrativaFinanceiraLista implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao ctx) throws Exception {
		// TODO Auto-generated method stub

		Registro[] linhas = ctx.getLinhas();

		Double cartaoDesc = (Double) ctx.getParam("CARTAODESC");
		
		for (Registro linha : linhas)
		{
			linha.setCampo("CARTAODESC", cartaoDesc);
			linha.save();
		}

		ctx.setMensagemRetorno("Taxa Administradora alterada com sucesso!");

	}


}