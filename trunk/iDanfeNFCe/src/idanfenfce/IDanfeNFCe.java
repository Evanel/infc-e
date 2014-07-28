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
package idanfenfce;

import com.is5.Danfe.Danfe;
import java.awt.Desktop;
import java.io.File;

/**
 *
 * @author Ivan Vargas
 */
public class IDanfeNFCe {

    /**
     * @param args the command line arguments
     */
    
    private Retorno retorno = new Retorno();
    
    private String protocolo = "";
    private String dhProtocolo = "";
    private String urlConsulta = "https://www.sefaz.rs.gov.br/NFCE/NFCE-COM.aspx";
    
    private String arquivoXML = "";
    private String idToken = "";
    private String token = "";
    private String arquivoDestino;
    
    private int sizeQrCode = 250;
    
    public void setSizeQrCode(int size) {
        this.sizeQrCode = size;
    }
    
    public Retorno getRetorno() {
        if (retorno == null) {
            retorno = new Retorno();
        }
        return retorno;
    }
    
    public void setRetorno(Retorno retorno) {
        this.retorno = retorno;
    }
    
    public void setArquivoDestino(String arquivoDestino) {
        this.arquivoDestino = arquivoDestino;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setArquivoXML(String arquivoXML) {
        this.arquivoXML = arquivoXML;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
    
    public void setDhProtocolo(String dhProtocolo) {
        this.dhProtocolo = dhProtocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public void setUrlConsulta(String urlConsulta) {
        this.urlConsulta = urlConsulta;
    }
    
    private String getArquivoDestino() {
        if (this.arquivoDestino == null) {
            try
            {
                this.arquivoDestino = this.arquivoXML.toLowerCase().replace(".xml", ".pdf");
                
            }catch(Exception ex) {
                //
            }
        }
        return this.arquivoDestino;
    }

     
    public boolean gerarDanfe() {
        
        try
        {
            //importar XML para Danfe
            XMLToDanfe xml2danfe = new XMLToDanfe();
            xml2danfe.setRetorno(this.retorno);
            
            xml2danfe.setProtocolo(this.protocolo);
            xml2danfe.setDhProtocolo(this.dhProtocolo);
            xml2danfe.setUrlConsulta(this.urlConsulta);
            xml2danfe.setArquivoXML(this.arquivoXML);
       

            Danfe danfe = xml2danfe.executar();

            if (danfe != null) 
            {
                //converter Danfe em PDF
                DanfeToPDF danfe2pdf = new DanfeToPDF();
                danfe2pdf.setRetorno(this.retorno);
                
                danfe2pdf.setDanfe(danfe);
                danfe2pdf.setPathPDF(this.getArquivoDestino());
                danfe2pdf.setUrlConsulta(this.urlConsulta);
                danfe2pdf.setSizeQrCode(this.sizeQrCode);
                danfe2pdf.setIdToken(this.idToken);
                danfe2pdf.setToken(this.token);
                

                if ( danfe2pdf.gerarPdf() )
                {
                    this.getRetorno().addAlerta( "Danfe gerado com sucesso: " + this.getArquivoDestino() );
                    return true;                
                }
                else
                {
                    this.getRetorno().addErro( "Não foi possível gerar Danfe:" + xml2danfe.getRetorno().getErros() );
                    return false;
                }    
            }
            else
            {
                this.getRetorno().addErro( "Não foi possível gerar Danfe:" + xml2danfe.getRetorno().getErros() );
                return false;
            }
            
        }catch(Exception ex) {
            this.getRetorno().addErro( "Erro ao gerar Danfe:" + ex.getMessage()) ;
            return false;
        }
 
    }
    
    public boolean imprimrir() {
        try
        {
            Desktop.getDesktop().print(new File(this.arquivoDestino)); 

            return true;
        }catch(Exception ex) {
            this.getRetorno().addErro("Erro ao imprimir Danfe: " + ex.getMessage() );
            return false;
        }
        
    }
    
   
}
