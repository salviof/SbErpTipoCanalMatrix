/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.fw.erp.implementacao.transportecomunicacao;

import br.org.coletivoJava.fw.erp.implementacao.transportecomunicacao.dados.UsuarioTestesComunicacao;
import br.org.coletivojava.erp.comunicacao.transporte.ERPTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDialogo;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ErroAcessandoCanalComunicacao;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import testesFW.TesteJUnitBasicoSemAcoes;

/**
 *
 * @author salvio
 */
public class MsgDisparoMatriximplTest extends TesteJUnitBasicoSemAcoes {

    public MsgDisparoMatriximplTest() {
    }

    /**
     * Test of dispararInicioComunicacao method, of class MsgDisparoMatriximpl.
     */
    @Test
    public void testDispararInicioComunicacao() {
        UsuarioTestesComunicacao usuarionotificacao = new UsuarioTestesComunicacao();
        ItfDialogo dialogo = SBCore.getServicoComunicacao().gerarComunicacaoSistema_Usuario(FabTipoComunicacao.NOTIFICAR,
                usuarionotificacao, "Apenas teste", "Mensagem do teste de notificação via email");
        try {
            SBCore.getServicoComunicacao().dispararComunicacao(dialogo, ERPTipoCanalComunicacao.MATRIX);
        } catch (ErroAcessandoCanalComunicacao ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha disparando comunicado pelo canal de email", ex);
        }
    }

}
