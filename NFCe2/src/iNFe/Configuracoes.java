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

import Enums.Estados;
import Enums.TiposAmbiente;
import Enums.Versoes;
import java.io.Serializable;
import nfe.util.StringEncryptor;

/**
 *
 * @author Ivan Vargas
 */
public class Configuracoes implements Serializable{
    
    private String KEYSTORE_PATH;
    private String KEYSTORE_SENHA;
    private String TRUSTSTORE_PATH;
    private String TRUSTSTORE_SENHA;
    private String MODELO_DANFE;
    private String FORMA_EMISSAO;
    private String UF_DESTINO;
    private String AMBIENTE;
    private String CNPJ;
    private String IE;
    private String RAZAO_SOCIAL;
    private String FANTASIA;
    private String FONE;
    private String CEP;
    private String LOGRADOURO;
    private String NUMERO;
    private String COMPLEMENTO;
    private String BAIRRO;
    private String COD_CIDADE;
    private String CIDADE;
    private String UF;
    private String HOST_PROXY;
    private String PORTA_PROXY;
    private String USUARIO_PROXY;
    private String SENHA_PROXY;
    private String HOST_SMTP;
    private String PORTA_SMTP;
    private String USUARIO_SMTP;
    private String SENHA_SMTP;
    private String ASSUNTO_MAIL;
    private String SSL_SMTP;
    private String MENSAGEM_MAIL;
    private String PATH_NFE;
    private String MOSTRAR_MENSAGEM;
    private String VERSAO;
    private String PATH_CONTINGENCIA;
    private String PATH_SCHEMAS;
    private String TOKEN;
    private String IDTOKEN;
    private String IM;
    private String CNAE;
    private String CRT;

    public String getCNAE() {
        return (CNAE != null) ? CNAE : "";
    }

    public void setCNAE(String CNAE) {
        this.CNAE = CNAE;
    }

    public String getCRT() {
        return (CRT != null) ? CRT : "";
    }

    public void setCRT(String CRT) {
        this.CRT = CRT;
    }

    public String getIM() {
        return (IM != null) ? IM : "";
    }

    public void setIM(String IM) {
        this.IM = IM;
    }
    
    public String getIDTOKEN() {
        return (IDTOKEN != null) ? IDTOKEN : "";
    }

    public void setIDTOKEN(String IDTOKEN) {
        this.IDTOKEN = IDTOKEN;
    }

    public String getTOKEN() {
        return (TOKEN != null) ? TOKEN : "";
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
    }
    
    public String getKEYSTORE_SENHA() {
        return (KEYSTORE_SENHA != null) ? new StringEncryptor().decrypt(KEYSTORE_SENHA) : "";
    }

    public void setKEYSTORE_SENHA(String KEYSTORE_SENHA) {
        this.KEYSTORE_SENHA = new StringEncryptor().encrypt(KEYSTORE_SENHA);
    }
    
    public String getTRUSTSTORE_SENHA() {
        return (TRUSTSTORE_SENHA != null) ? new StringEncryptor().decrypt(TRUSTSTORE_SENHA) : "";
    }

    public void setTRUSTSTORE_SENHA(String TRUSTSTORE_SENHA) {
        this.TRUSTSTORE_SENHA = new StringEncryptor().encrypt(TRUSTSTORE_SENHA);
    }
    
    public String getPATH_CONTINGENCIA() {
        //return ( != null) ? PATH_CONTINGENCIA : "";
        return formatarPath(PATH_CONTINGENCIA);
    }

    public void setPATH_CONTINGENCIA(String PATH_CONTINGENCIA) {
        this.PATH_CONTINGENCIA = PATH_CONTINGENCIA;
    }
    
    public String getAMBIENTE() {
        return (AMBIENTE != null) ? AMBIENTE : "";
    }

    public void setAMBIENTE(String AMBIENTE) {
        this.AMBIENTE = AMBIENTE;
    }

    public String getASSUNTO_MAIL() {
        return (ASSUNTO_MAIL != null) ? ASSUNTO_MAIL : "";
    }

    public void setASSUNTO_MAIL(String ASSUNTO_MAIL) {
        this.ASSUNTO_MAIL = ASSUNTO_MAIL;
    }

    public String getBAIRRO() {
        return (BAIRRO != null) ? BAIRRO : "";
    }

    public void setBAIRRO(String BAIRRO) {
        this.BAIRRO = BAIRRO;
    }

    public String getCEP() {
        return (CEP != null) ? CEP : "";
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getCIDADE() {
        return (CIDADE != null) ? CIDADE : "";
    }

    public void setCIDADE(String CIDADE) {
        this.CIDADE = CIDADE;
    }

    public String getCNPJ() {
        return (CNPJ != null) ? CNPJ : "";
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getCOD_CIDADE() {
        return (COD_CIDADE != null) ? COD_CIDADE : "";
    }

    public void setCOD_CIDADE(String COD_CIDADE) {
        this.COD_CIDADE = COD_CIDADE;
    }

    public String getCOMPLEMENTO() {
        return (COMPLEMENTO != null) ? COMPLEMENTO : "";
    }

    public void setCOMPLEMENTO(String COMPLEMENTO) {
        this.COMPLEMENTO = COMPLEMENTO;
    }

    public String getFANTASIA() {
        return (FANTASIA != null) ? FANTASIA : "";
    }

    public void setFANTASIA(String FANTASIA) {
        this.FANTASIA = FANTASIA;
    }

    public String getFONE() {
        return (FONE != null) ? FONE : "";
    }

    public void setFONE(String FONE) {
        this.FONE = FONE;
    }

    public String getFORMA_EMISSAO() {
        return ( FORMA_EMISSAO != null) ? FORMA_EMISSAO : "";
    }

    public void setFORMA_EMISSAO(String FORMA_EMISSAO) {
        this.FORMA_EMISSAO = FORMA_EMISSAO;
    }
    
    
    public String getHOST_PROXY() {
        return (HOST_PROXY != null) ? HOST_PROXY : "";
    }

    public void setHOST_PROXY(String HOST_PROXY) {
        this.HOST_PROXY = HOST_PROXY;
    }

    public String getHOST_SMTP() {
        return (HOST_SMTP != null) ? HOST_SMTP : "";
    }

    public void setHOST_SMTP(String HOST_SMTP) {
        this.HOST_SMTP = HOST_SMTP;
    }

    public String getIE() {
        return (IE != null) ? IE : "";
    }

    public void setIE(String IE) {
        this.IE = IE;
    }

    public String getKEYSTORE_PATH() {
        return (KEYSTORE_PATH != null) ? KEYSTORE_PATH : "";
    }

    public void setKEYSTORE_PATH(String KEYSTORE_PATH) {
        this.KEYSTORE_PATH = KEYSTORE_PATH;
    }

    public String getLOGRADOURO() {
        return (LOGRADOURO != null) ? LOGRADOURO : "";
    }

    public void setLOGRADOURO(String LOGRADOURO) {
        this.LOGRADOURO = LOGRADOURO;
    }

    public String getMENSAGEM_MAIL() {
        return (MENSAGEM_MAIL != null) ? MENSAGEM_MAIL : "";
    }

    public void setMENSAGEM_MAIL(String MENSAGEM_MAIL) {
        this.MENSAGEM_MAIL = MENSAGEM_MAIL;
    }

    public String getMODELO_DANFE() {
        return (MODELO_DANFE != null) ? MODELO_DANFE : "";
    }

    public void setMODELO_DANFE(String MODELO_DANFE) {
        this.MODELO_DANFE = MODELO_DANFE;
    }

    public String getMOSTRAR_MENSAGEM() {
        return (MOSTRAR_MENSAGEM != null) ? MOSTRAR_MENSAGEM : "";
    }

    public void setMOSTRAR_MENSAGEM(String MOSTRAR_MENSAGEM) {
        this.MOSTRAR_MENSAGEM = MOSTRAR_MENSAGEM;
    }

    public String getNUMERO() {
        return (NUMERO != null) ? NUMERO : "";
    }

    public void setNUMERO(String NUMERO) {
        this.NUMERO = NUMERO;
    }

    public String getPATH_NFE() {
        //return ( != null) ? PATH_NFE : "";
        return formatarPath(PATH_NFE);
    }

    public void setPATH_NFE(String PATH_NFE) {
        this.PATH_NFE = PATH_NFE;
    }

    public String getPORTA_PROXY() {
        return (PORTA_PROXY != null) ? PORTA_PROXY : "";
    }

    public void setPORTA_PROXY(String PORTA_PROXY) {
        this.PORTA_PROXY = PORTA_PROXY;
    }

    public String getPORTA_SMTP() {
        return (PORTA_SMTP != null) ? PORTA_SMTP : "";
    }

    public void setPORTA_SMTP(String PORTA_SMTP) {
        this.PORTA_SMTP = PORTA_SMTP;
    }

    public String getRAZAO_SOCIAL() {
        return (RAZAO_SOCIAL != null) ? RAZAO_SOCIAL : "";
    }

    public void setRAZAO_SOCIAL(String RAZAO_SOCIAL) {
        this.RAZAO_SOCIAL = RAZAO_SOCIAL;
    }

    public String getSENHA_PROXY() {
        return (SENHA_PROXY != null) ? new StringEncryptor().decrypt(SENHA_PROXY) : "";
    }

    public void setSENHA_PROXY(String SENHA_PROXY) {
        this.SENHA_PROXY = new StringEncryptor().encrypt(SENHA_PROXY);
    }

    public String getSENHA_SMTP() {
        return (SENHA_SMTP != null) ? new StringEncryptor().decrypt(SENHA_SMTP) : "";
    }

    public void setSENHA_SMTP(String SENHA_SMTP) {
        this.SENHA_SMTP = new StringEncryptor().encrypt(SENHA_SMTP);
    }

    public String getSSL_SMTP() {
        return (SSL_SMTP != null) ? SSL_SMTP : "";
    }

    public void setSSL_SMTP(String SSL_SMTP) {
        this.SSL_SMTP = SSL_SMTP;
    }

    public String getTRUSTSTORE_PATH() {
        return (TRUSTSTORE_PATH != null) ? TRUSTSTORE_PATH : "";
    }

    public void setTRUSTSTORE_PATH(String TRUSTSTORE_PATH) {
        this.TRUSTSTORE_PATH = TRUSTSTORE_PATH;
    }

    public String getUF() {
        return (UF != null) ? UF : "";
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getUF_DESTINO() {
        return (UF_DESTINO != null) ? UF_DESTINO : "";
    }

    public void setUF_DESTINO(String UF_DESTINO) {
        this.UF_DESTINO = UF_DESTINO;
    }

    public String getUSUARIO_PROXY() {
        return (USUARIO_PROXY != null) ? USUARIO_PROXY : "";
    }

    public void setUSUARIO_PROXY(String USUARIO_PROXY) {
        this.USUARIO_PROXY = USUARIO_PROXY;
    }

    public String getUSUARIO_SMTP() {
        return (USUARIO_SMTP != null) ? new StringEncryptor().decrypt(USUARIO_SMTP) : "";
    }

    public void setUSUARIO_SMTP(String USUARIO_SMTP) {
        this.USUARIO_SMTP = new StringEncryptor().encrypt(USUARIO_SMTP);
    }

    public String getVERSAO() {
        return (VERSAO != null) ? VERSAO : "";
    }

    public void setVERSAO(String VERSAO) {
        this.VERSAO = VERSAO;
    }

    public String getPATH_SCHEMAS() {
          //return (PATH_SCHEMAS != null) ? PATH_SCHEMAS : "";
          return formatarPath(PATH_SCHEMAS);
    }

    public void setPATH_SCHEMAS(String PATH_SCHEMAS) {
        this.PATH_SCHEMAS = PATH_SCHEMAS;
    }
    
    private String formatarPath(String path) {
        if (path != null) 
        {
            path = path.replace("\\", "/").trim();
            if (path.endsWith("/"))
                return path;
            else
                return path + "/";
        }
        else
            return "";
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    public Estados getEstado() {
        return getEstado(this.UF);
    }
    
    public Versoes getVersao() {        
        return getVersao(this.VERSAO);
    }
    
    public TiposAmbiente getAmbiente() {
        return getAmbiente(this.AMBIENTE);
    }
    
    public Estados getEstado(String UF) 
    {
        try
        {
            for(Estados e : Estados.values()) {
                if ( e.name().equals(UF.toUpperCase().trim()) ) {
                    return e;
                }
            }        
        }catch(Exception e) {
            System.out.println("Erro em getEstado(): " + e.getMessage());
        }
        return null;        
    }
    
    public Versoes getVersao(String versao) 
    {        
        try
        {
            for(Versoes v : Versoes.values()) {
                if ( v.getVersao().equals(versao)) {
                    return v;
                }
            }
        }catch(Exception e) {
            System.out.println("Erro em getVersao(): " + e.getMessage());
        }
        return null;
    }
    
    public TiposAmbiente getAmbiente(String Ambiente) 
    {
        try
        {
            return (Ambiente.equals("1")) ? TiposAmbiente.PRODUCAO : TiposAmbiente.HOMOLOGACAO;
        }catch(Exception e) {
            System.out.println("Erro em getAmbiente(): " + e.getMessage());
        }
        return null;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    
}
