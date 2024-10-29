package br.com.sankhya.actcasacontente;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
//import java.math.BigDecimal;

public class ActAlterarTipoFrete implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao ctx) throws Exception {
		// TODO Auto-generated method stub

		Registro[] linhas = ctx.getLinhas();

		String tipFrete = (String) ctx.getParam("TIPFRETE");

		for (Registro linha : linhas)
		{
			linha.setCampo("TIPFRETE", tipFrete);
			linha.save();
		}

		ctx.setMensagemRetorno("Tipo de Frete alterado com sucesso!");

	}


}

