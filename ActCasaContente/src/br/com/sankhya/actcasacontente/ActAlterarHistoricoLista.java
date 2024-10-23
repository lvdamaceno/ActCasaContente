package br.com.sankhya.actcasacontente;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
//import java.math.BigDecimal;

public class ActAlterarHistoricoLista implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao ctx) throws Exception {
		// TODO Auto-generated method stub

		Registro[] linhas = ctx.getLinhas();

		String historico = (String) ctx.getParam("HISTORICO");

		for (Registro linha : linhas)
		{
			linha.setCampo("HISTORICO", historico);
			linha.save();
		}

		ctx.setMensagemRetorno("Histórico alterado com sucesso!");

	}


}