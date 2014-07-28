/*******************************************************************************
* Projeto iNFC-e                                                               *
* Emissao de NFC-e em Java                                                     *
*                                                                              *
* Direitos Autorais Reservados (c) 2014 Ivan S. Vargas                         *
*                                                                              *
*  Voc� pode obter a �ltima vers�o desse arquivo na pagina do Projeto iNFC-e   *
* localizado em https://code.google.com/p/infc-e/                              *
*                                                                              *
*  Esta biblioteca � software livre; voc� pode redistribu�-la e/ou modific�-la *
* sob os termos da Licen�a P�blica Geral Menor do GNU conforme publicada pela  *
* Free Software Foundation; tanto a vers�o 2.1 da Licen�a, ou (a seu crit�rio) *
* qualquer vers�o posterior.                                                   *
*                                                                              *
*  Esta biblioteca � distribu�da na expectativa de que seja �til, por�m, SEM   *
* NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU      *
* ADEQUA��O A UMA FINALIDADE ESPEC�FICA. Consulte a Licen�a P�blica Geral Menor*
* do GNU para mais detalhes. (Arquivo LICEN�A.TXT ou LICENSE.TXT)              *
*                                                                              *
*  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral Menor do GNU junto*
* com esta biblioteca; se n�o, escreva para a Free Software Foundation, Inc.,  *
* no endere�o 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          *
* Voc� tamb�m pode obter uma copia da licen�a em:                              *
* http://www.opensource.org/licenses/lgpl-license.php                          *
*                                                                              *
*        Ivan S. Vargas  -  ivan@is5.com.br  -  http://www.is5.com.br          *
*                                                                              *
********************************************************************************/
package webservices;

import Enums.*; 
import iNFe.Configuracao;
import java.io.*;
import java.net.URL;
import java.security.Security;
import javax.xml.soap.*;
import nfe.util.Log;


/**
 *
 * @author Ivan Vargas
 */
public class WebServices {
    
    protected Configuracao c;
    protected TiposAmbiente tpamb;
    protected Estados cuf;
    protected Versoes versao;
    
    public void setEstado(Estados cuf) {
        this.cuf = cuf;
    }

    public void setAmbiente(TiposAmbiente tpamb) {
        this.tpamb = tpamb;
    }

    public void setVersao(Versoes versao) {
        this.versao = versao;
    }
    
    private String getUrl(Servicos servico) {
        try
        {
            return new GetWebService().getURL(this.cuf, this.tpamb, this.versao, servico);
        }catch(Exception ex) {
            return null;
        }
    }
    
    public WebServices() 
    {
        try
        {
           this.c = Configuracao.getInstance();
           
           this.tpamb  = c.getConfiguracoes().getAmbiente();
           this.cuf    = c.getConfiguracoes().getEstado();
           this.versao = c.getConfiguracoes().getVersao(); 
           
           System.out.println("Razao Social: " + c.getConfiguracoes().getRAZAO_SOCIAL() + "\ntpAmb: " + this.tpamb + "\ncUf: " + this.cuf + "\nVersao: " + this.versao);
            
        }catch(Exception ex) {
           Log.getInstance().addErro("Erro ao instanciar WebService: " + ex.getMessage());
        }
    }
    
    public String invoke(String envelope, Servicos servico) 
    {
        try {
            String urlAddress = getUrl(servico);
            if (urlAddress == null) {
                Log.getInstance().addErro("N�o foi poss�vel retornar URL do WebService.");
                return null;
            }
            
            //Configuracao c = new Configuracao();
            //if (c == null) {
            //    Log.getInstance().addErro("ws.invoke(): Imposs�vel carrregar configuracoes.");
            //    return null;
            //}
            
            try 
            {
                MessageFactory factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
                SOAPMessage message;

                System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
                Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                
                /*
                 * limpo propriedades
                 */
                System.clearProperty("javax.net.ssl.keyStore");
                System.clearProperty("javax.net.ssl.keyStorePassword");
                System.clearProperty("javax.net.ssl.trustStore");

                /*
                 * dados do certificado do cliente
                 */
                System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
                System.setProperty("javax.net.ssl.keyStore", c.getConfiguracoes().getKEYSTORE_PATH() );
                System.setProperty("javax.net.ssl.keyStorePassword", c.getConfiguracoes().getKEYSTORE_SENHA() );
                System.out.println("KeyStore: " + c.getConfiguracoes().getKEYSTORE_PATH() + "\nSenha KS: " + c.getConfiguracoes().getKEYSTORE_SENHA() );

                /*
                 * dados do certificado do servidor
                 */
                System.setProperty("javax.net.ssl.trustStoreType", "JKS");
                System.setProperty("javax.net.ssl.trustStore", c.getConfiguracoes().getTRUSTSTORE_PATH() );
                System.setProperty("javax.net.ssl.trustStorePassword", c.getConfiguracoes().getTRUSTSTORE_SENHA() );
                System.out.println("TrusStore: " + c.getConfiguracoes().getTRUSTSTORE_PATH() + "\nSenha TS: " + c.getConfiguracoes().getTRUSTSTORE_SENHA() );
                
                /* Url do WebService */
                URL url = new URL(urlAddress);

                /* tipo de mensagem: SOAP */
                MimeHeaders header = new MimeHeaders();
                header.addHeader("Content-Type", "application/soap+xml");

                /* monta a mensagem SOAP */
                message = factory.createMessage(header, new ByteArrayInputStream(envelope.getBytes()));
                
                /* instancia uma conexao SOAP */
                SOAPConnection conSoap = SOAPConnectionFactory.newInstance().createConnection();
                
                /* Envia a mensagem SOAP ao WebService */
                SOAPMessage resWs = conSoap.call(message, url);

                //ByteArrayOutputStream in = new ByteArrayOutputStream();
                //message.writeTo(in);
                //System.out.println("in :\n" + in.toString());

                /* Mostra a mensagem de retorno */
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                resWs.writeTo(out);
                System.out.println("out :\n" + desnormalizar(out.toString()));
                
                /* gravo o retorno em um arquivo */
                BufferedWriter bw = new BufferedWriter(new FileWriter(c.getConfiguracoes().getPATH_NFE()+"ultimo_retorno.xml"));
                bw.write(out.toString());
                bw.close();
               
                return out.toString();

            } catch (IOException ex) {
                Log.getInstance().addErro("Erro ao carregar Cerfiticados: " + ex.getMessage());
                return null;
            } 
            
        } catch (SOAPException ex) {
            System.out.println("" + ex.getMessage());
            Log.getInstance().addErro("Erro ao acessar WebService: " + ex.getMessage() );
            return null;
        }
    }
    
    private String desnormalizar(String texto) {
        return texto.replace("<", "<").replace(">", ">").replace("''", "\"").replace(" ", "\r");
    }
    
}
