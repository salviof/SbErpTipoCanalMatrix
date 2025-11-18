package br.org.coletivoJava.fw.erp.implementacao.transportecomunicacao;

import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroConexaoServicoChat;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoUsuarioChat;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDialogo;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.repositorioLinkEntidades.RepositorioLinkEntidadesGenerico;
import br.org.coletivoJava.fw.api.erp.transportecomunicacao.MsgDisparoMatrix;
import br.org.coletivoJava.fw.erp.implementacao.chat.ChatMatrixOrgimpl;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

@MsgDisparoMatrix
public class MsgDisparoMatriximpl extends RepositorioLinkEntidadesGenerico
        implements
        com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDisparoComunicacao {

    private static ChatMatrixOrgimpl chatMatrix = (ChatMatrixOrgimpl) ERPChat.MATRIX_ORG.getImplementacaoDoContexto();

    @Override
    public String dispararInicioComunicacao(ItfDialogo pDialogo) {

        String email = pDialogo.getDestinatario().getUsuario().getEmail();
        ComoUsuarioChat usuario = null;
        try {
            usuario = chatMatrix.getUsuarioByEmail(email);
        } catch (ErroConexaoServicoChat ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha buscando usuário por e-mail" + email, ex);
            return null;
        }

        if (usuario != null) {
            try {
                String reciboEntrega = chatMatrix.enviarDirect(usuario.getCodigoUsuario(), pDialogo.getAssunto() + " " + pDialogo.getMensagem());
                return reciboEntrega;
            } catch (ErroConexaoServicoChat ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, email, ex);
            }
        } else {
            System.out.println(email + " não encontrado no serviço Matrix");
            return null;
        }

        return null;
    }

    @Override
    public void dispararRespostaComunicacao(
            ItfDialogo itfDialogo) {

    }
}
