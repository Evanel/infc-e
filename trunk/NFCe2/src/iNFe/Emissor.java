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
package iNFe;

import java.io.*;
import nfe.util.Log;
import nfe.util.Mensagem;
import nfe.util.NFeValidacaoXML;
import nfe.util.Util;
import webservices.NfeRecepcao2;
import webservices.NfeRetRecepcao2;

/**
 *
 * @author Ivan Vargas
 */
public class Emissor {
    
    //private static final String SCHEMA_VALIDACAO = "C:/Schemas/enviNFe_v3.10.xsd";
    
    private static final String SCHEMA_VALIDACAO = Configuracao.getInstance().getConfiguracoes().getPATH_SCHEMAS() + "enviNFe_v3.10.xsd";
    
    private NFe2 nfe;
    private String nLote = null;    
    private String filename = null;
    
    public NfeRecepcao2 Recepcao;
    public NfeRetRecepcao2 RetornoRecepcao;
    
    private Mensagem mensagem;
    
    public Mensagem getMensagem() {
        if (this.mensagem == null) {
            this.mensagem = new Mensagem();
        }
        return this.mensagem;
    }
    
    private void load() 
    {
        this.Recepcao = new NfeRecepcao2();
        this.RetornoRecepcao = new NfeRetRecepcao2();        
    }
    
    public Emissor() 
    {
        this.load();
    }
    
    public Emissor(NFe2 nfe) 
    {
        this.load();
        this.setNFe(nfe);   
    }
    
    public void setNFe(NFe2 nfe) {
        this.nfe = nfe;
        this.Recepcao.setNFe(nfe);
        this.RetornoRecepcao.setNFe(this.nfe);
    }
    
    public void setLote(String Lote) {
        this.nLote = Lote;
    }
            
    private boolean gerarNota() 
    {
        Log.getInstance().addMensagem("Gerando NFC-e...");
        /*
         * Grava a nota em arquivo .xml
         */
        this.filename = null;
        if (this.nLote == null)
            this.nLote = "1";
        this.nfe.setLote(nLote);
        try
        {
            String xml = nfe.getXML();
            if (xml != null)
            {
                filename = Util.getNomeArquivoNotaFiscal(nfe);
                BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
                bw.write(xml);
                bw.close();
                Log.getInstance().addMensagem("Nota Gerada: " + this.filename);
                nfe.setPathXml(this.filename);
                return true;
            }
            else
            {
                Log.getInstance().addErro("Não foi possivel gerar XML da Nota Fiscal");
                return false;
            }
        }
        catch(Exception ex) 
        {
            Log.getInstance().addErro("Erro ao gerar Nota: " + ex.getMessage());
            return false;
        }
    }
    
    private boolean assinarNota() 
    {
        Log.getInstance().addMensagem("Assinando arquivo XML...");
        
        if (filename != null)
        {
            return Util.Assinar(filename);
        }
        else
        {
            getMensagem().addErro("Não foi possível assinar Nota Fiscal");
            Log.getInstance().addErro(nLote);
            return false;
        }
    }
    
    private boolean validarNota() 
    {
        Log.getInstance().addMensagem("Validando XML...");
        
        try
        {
            StringBuilder sbXML = new StringBuilder();
            String linha = "";
            String xml = "";

            /* add cabecalho para a validacao */
            sbXML.append("<enviNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"3.10\">");
            sbXML.append("<idLote>").append(this.nLote).append("</idLote>").append("<indSinc>0</indSinc>");
            
            /* retorna conteudo nota fiscal */
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "ISO-8859-1"));  
            while ((linha = in.readLine()) != null) {  
                sbXML.append(linha);  
            }  
            in.close(); 
            
            /* add rodape */
            sbXML.append("</enviNFe>");
            
            /* remove indicacao de xml q soh deve ter uma por arquivo */
            xml = sbXML.toString();
            xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", "")
                     .replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "")
                     .replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
            xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + xml.trim();
            
            Util.SaveToFile(xml, "C:/TESTE.XML"); //para depuracao
            
            //teste: gravo aki soh pra enviar mesmo q de erro... ISSO EH UM TESTE
            //nfe.xmlValidado = xml;
            //Util.SaveToFile(nfe.xmlValidado, new Configuracao().getPATH_NFE()+ nfe.getLote().trim() + "-env-lote.xml");
            
            /* valida */
            NFeValidacaoXML v = new NFeValidacaoXML();
            if (!v.validar(SCHEMA_VALIDACAO, new StringBuilder(xml)))
            {
                getMensagem().addErro("Erro de Schema");
                for(String s : v.getListaComErrosDeValidacao()) {                    
                    Log.getInstance().addErro(s);
                }
                return false;
            }
            else
            {
                nfe.xmlValidado = xml;
                Util.SaveToFile(nfe.xmlValidado, Configuracao.getInstance().getConfiguracoes().getPATH_NFE()+ nfe.getLote().trim() + "-env-lote.xml");
                return true;
            }
        }
        catch(Exception ex) 
        {
            Log.getInstance().addErro("Erro: " + ex.getMessage());
            return false;
        }
    }
    
    public boolean EmitirNotaFiscal() 
    {
        if (this.nfe == null) 
        {
            Log.getInstance().addErro("Nenhuma Nota Fiscal especificada.");
            return false;
        }
        
        Log.getInstance().addMensagem("Emitindo NFC-e. Aguarde...");
        
        /* gera a nfe */
        if ( gerarNota() )
        {
            /* assina a nfe */
            if ( assinarNota() )
            {
                /* valida o xml da nfe */
                if( validarNota() )
                {
                    /* envia a nfe para o ws NFeRecepcao2 */
                    if (!Recepcao.Executar())
                    {
                        /* se ocorreu erro a execucao, retorno os erros localmente */
                        //getMensagem().addErro("Erro em NFeRecepcao2");
                        //getMensagem().addAll(Recepcao.getMensagens());                                         
                    }
                    else
                    {
                        /* se nao deu nenhum erro ao enviar, busco o retorno no ws NFeRetRecepcao2 */
                        //try { Thread.sleep(5000); }catch(Exception ex) { } //tempo pro sefaz processar o lote 
                        RetornoRecepcao.setNRec(Recepcao.Retorno.infRec.nRec);
                        if(!RetornoRecepcao.Executar())
                        {
                            /* se deu algum erro ao processar, retorno os erros localmente */
                            //getMensagem().addErro("Erro em NFeRetRecepcao2");
                            //getMensagem().addAll(RetornoRecepcao.getMensagens());                            
                        }
                        else 
                        {
                            /*
                            * Retorna sucesso somente se a recepcao responder com 100
                            * que corresponde a "Autorizado o Uso da Nota"
                            * Se esta funcao retornar false, deve percorrer o array
                            * de Erros, uma vez que copiei todos os erros para este mesmo
                            * array.
                            * Mudei isso. Agora, pra saber se foi aceita pelo sefaz,
                            * deve-se chamar a funcao AutorizadoUso()
                            */
                            //return RetornoRecepcao.infProt.cStat.equals("100");
                            return true;
                        }
                    }
                }          
            }
        }
        /* por padrao retorna false */
        return false;
    }
    
    public boolean AutorizadoUso() 
    {
        try
        {
            /* 
             * Apos enviado um lote ele fica em processamento. A respota de autorizacao pode
             * demorar alguns segundos. Se o lote estiver sendo processado o RetRecepcao.cStat = 105
             * O recebimento de um lote nao garante que o mesmo tenha sido aceito.
             * Primeiro o sefaz recebe o lote, depois valida... Por isso devemos acessar o ws
             * NFe2RetRecepcao2 para verificar se a nfe do lote foi autorizada (infProt.cStat = 105) 
             * ou nao.
             */
           
           if (RetornoRecepcao.Retorno.cStat.equals("104")) //se foi processado
           {
               return RetornoRecepcao.Retorno.infProt.cStat.equals("100");
           }
           else
           {
               while (RetornoRecepcao.Retorno.cStat.equals("105")) //se esta sendo processado....
               {
                   try 
                   {  
                       Thread.sleep(5000); //aguarda 5 segundos
                       Log.getInstance().addMensagem("Lote sendo processado. Aguardar mais 5 segundos...");
                   }catch(Exception ex) 
                   {
                       //
                   }
                   RetornoRecepcao.setNRec(Recepcao.Retorno.infRec.nRec);
                   RetornoRecepcao.Executar();
               }
               return RetornoRecepcao.Retorno.infProt.cStat.equals("100");
           }
        }
        catch(Exception e) {
            getMensagem().addErro(e.getMessage());
            Log.getInstance().addErro("Erro: " + e.getMessage());
            return false;
        }
    }
}
