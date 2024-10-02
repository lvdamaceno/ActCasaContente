package br.com.sankhya.actcasacontente;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
//import java.math.BigDecimal;

public class ActAlterarCifFobCabecalho implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao ctx) throws Exception {
		// TODO Auto-generated method stub

		Registro[] linhas = ctx.getLinhas();

		String cif_fob = (String) ctx.getParam("CIF_FOB");

		for (Registro linha : linhas)
		{
			linha.setCampo("CIF_FOB", cif_fob);
			linha.save();
		}

		ctx.setMensagemRetorno("Tipo de Frete alterado com sucesso!");

	}


}

