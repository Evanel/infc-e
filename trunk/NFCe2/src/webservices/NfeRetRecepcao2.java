/*******************************************************************************
* Projeto iNFC-e                                                               *
* Emissao de NFC-e em Java                                                     *
*                                                                              *
* Direitos Autorais Reservados (c) 2014 Ivan S. Vargas                         *
*                                                                              *
*  Você pode obter a última versão desse arquivo na pagina do Projeto iNFC-e   *
* localizado em https://code.google.com/p/infc-e/                              *
*                                                                              *
*  Esta biblioteca é software livre; você pode redistribuí-la e/ou modificá-la *
* sob os termos da Licença Pública Geral Menor do GNU conforme publicada pela  *
* Free Software Foundation; tanto a versão 2.1 da Licença, ou (a seu critério) *
* qualquer versão posterior.                                                   *
*                                                                              *
*  Esta biblioteca é distribuída na expectativa de que seja útil, porém, SEM   *
* NENHUMA GARANTIA; nem mesmo a garantia implícita de COMERCIABILIDADE OU      *
* ADEQUAÇÃO A UMA FINALIDADE ESPECÍFICA. Consulte a Licença Pública Geral Menor*
* do GNU para mais detalhes. (Arquivo LICENÇA.TXT ou LICENSE.TXT)              *
*                                                                              *
*  Você deve ter recebido uma cópia da Licença Pública Geral Menor do GNU junto*
* com esta biblioteca; se não, escreva para a Free Software Foundation, Inc.,  *
* no endereço 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          *
* Você também pode obter uma copia da licença em:                              *
* http://www.opensource.org/licenses/lgpl-license.php                          *
*                                                                              *
*        Ivan S. Vargas  -  ivan@is5.com.br  -  http://www.is5.com.br          *
*                                                                              *
********************************************************************************/
package webservices;

import Enums.Servicos;
import iNFe.NFe2;
import java.io.File;
import java.text.MessageFormat;
import nfe.util.GetFieldsXML;
import nfe.util.Log;
import nfe.util.Util;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import webservices.Retornos.RetornoRetRecepcao;

/**
 *
 * @author Ivan Vargas
 */

/* Exemplo arquivo retorno
  
 * <retConsReciNFe versao="2.00" xmlns="http://www.portalfiscal.inf.br/nfe">
    <tpAmb>2</tpAmb>
    <verAplic>RS20131129092745</verAplic>
    <nRec>431000023700471</nRec>
    <cStat>104</cStat>
    <xMotivo>Lote processado</xMotivo>
    <cUF>43</cUF>
    <protNFe versao="2.00">
        <infProt Id="ID143130006707136">
            <tpAmb>2</tpAmb>
            <verAplic>RS20131129092745</verAplic>
            <chNFe>43131201600275000124550010000009871000009872</chNFe>
            <dhRecbto>2013-12-02T11:28:47</dhRecbto>
            <nProt>143130006707136</nProt>
            <digVal>zmnP6uGRnUxjJl+WsA7e5z4PMO8=</digVal>
            <cStat>100</cStat>
            <xMotivo>Autorizado o uso da NF-e</xMotivo>
        </infProt>
    </protNFe>
  </retConsReciNFe>
  
 */

public class NfeRetRecepcao2 extends WebServices {
    /*
     * Consulta Processamento de Lote de NF-e
     * 
     * Metodo: nfeRetRecepcao
     * Schema Envio: consReciNFe_v2.00.xsd
     * Schema Retorno: retConsReciNFe_v2.00.xsd
     */
   
    public RetornoRetRecepcao Retorno;

    private NFe2 nfe2;
    private String nrec;
    
        
    public NfeRetRecepcao2() {
        this.Retorno = new RetornoRetRecepcao();
    }    
        
    public void setNFe(NFe2 nfe2) {
        this.nfe2 = nfe2;
    }
    
    public void setNRec(String nRec) {
        this.nrec = nRec;
    }
  
    public boolean Executar() 
    {
        try
        {
            if ( (this.nfe2 == null) || (this.nrec == null) ) 
            {
                Log.getInstance().addErro("NFe ou nRec não especificados.");
                return false;
            }

            String msg = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                        + "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
                        + "<soap12:Header>"
                        + "<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetAutorizacao\">"
                        + "<cUF>{0}</cUF>"
                        + "<versaoDados>{1}</versaoDados>"
                        + "</nfeCabecMsg>"
                        + "</soap12:Header>"
                        + "<soap12:Body>"
                        + "<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetAutorizacao\">"
                            + "<consReciNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"{2}\">"
                            + "<tpAmb>{3}</tpAmb>"
                            + "<nRec>{4}</nRec>"
                            + "</consReciNFe>"
                        + "</nfeDadosMsg>"
                        + "</soap12:Body>"
                        + "</soap12:Envelope>";

            String envelope = MessageFormat.format(msg, this.cuf.getCodigo(), //nfe2.ide.getCUF(),
                                                        this.versao.getVersao(), //"3.10"/*nfe2.getVersao()*/,
                                                        this.versao.getVersao(), //"2.00",
                                                        this.tpamb.getTpAmb(), //nfe2.ide.getTpAmb(),
                                                        this.nrec);

            //Salva o arquivo com o pedido do Resultado do Processamento de NF-e
            Util.SaveToFile(GetFieldsXML.getMensagem12(envelope), c.getConfiguracoes().getPATH_NFE() + this.nrec + "-ped-rec.xml");

            //String retorno = WebServices.Invoke(envelope, "https://homologacao.nfe.sefaz.rs.gov.br/ws/Nferetrecepcao/NFeRetRecepcao2.asmx");
            //String retorno = WebServices.Invoke(envelope, "https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx");
            //String url = new GetWebService().getURL(cuf, tpamb, versao, Servicos.RETRECEPCAO);
            String retorno = invoke(envelope, Servicos.RETRECEPCAO);

            if (retorno != null) 
            {
                //Salva o arquivo com o resultado do processamento do lote
                String arquivoRetorno = c.getConfiguracoes().getPATH_NFE() + this.nrec + "-pro-rec.xml";

                String msgRetorno = GetFieldsXML.getMensagem(retorno);

                if ( (msgRetorno != null) && (!msgRetorno.equals("")) ) 
                {
                    msgRetorno = msgRetorno.replace("<nfeRetAutorizacaoLoteResult xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetAutorizacao\">", "").replace("</nfeRetAutorizacaoLoteResult>", "");
                    Util.SaveToFile(msgRetorno, arquivoRetorno);

                    try
                    {
                        File f = new File(arquivoRetorno);

                        SAXBuilder b = new SAXBuilder();
                        Document doc = b.build(f);

                        Element root = doc.getRootElement(); 

                        this.Retorno.cStat = Util.getValue(root, "cStat");
                        this.Retorno.cUF = Util.getValue(root, "cUF");
                        this.Retorno.nRec = Util.getValue(root, "nRec");
                        this.Retorno.tpAmb = Util.getValue(root, "tpAmb");
                        this.Retorno.verAplic = Util.getValue(root, "verAplic");
                        this.Retorno.xMotivo = Util.getValue(root, "xMotivo");

                        Element prot = root.getChild("protNFe", root.getNamespace());

                        if (prot != null) 
                        {
                            Element inf = prot.getChild("infProt", root.getNamespace());

                            this.Retorno.infProt.cStat = Util.getValue(inf, "cStat");
                            this.Retorno.infProt.chNFe = Util.getValue(inf, "chNFe");
                            this.Retorno.infProt.dhRecbto = Util.getValue(inf, "dhRecbto");
                            this.Retorno.infProt.digVal = Util.getValue(inf, "digVal");
                            this.Retorno.infProt.nProt = Util.getValue(inf, "nProt");
                            this.Retorno.infProt.tpAmb = Util.getValue(inf, "tpAmb");
                            this.Retorno.infProt.verAplic = Util.getValue(inf, "verAplic");
                            this.Retorno.infProt.xMotivo = Util.getValue(inf, "xMotivo");

                        }

                        return true;

                    }catch(Exception ex) {
                        Log.getInstance().addErro("Erro em NfeRetRecepcao.LerRetorno: " + ex.getMessage() );
                        return false;
                    }
                } 
                else {
                    Log.getInstance().addErro("Impossível ler mensagem de retorno em NfeRetRecepcao");
                    return false;
                }
            }
            else {
                Log.getInstance().addErro("O WebService NfeRetRecepcao não retornou dados.");
                return false;
            }
       }catch(Exception ex) {
           Log.getInstance().addErro("Erro em NfeRetRecepcao.Executar(): " + ex.getMessage());
           return false;
       }
        
    }
    
}
