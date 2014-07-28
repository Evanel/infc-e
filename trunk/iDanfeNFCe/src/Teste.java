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
import QrCode.GerarUrlQrCode;
import com.is5.iQrCode;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import idanfenfce.IDanfeNFCe;
import java.awt.Desktop;
import java.io.File;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ivan Vargas
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        idanfenfce.IDanfeNFCe d = new IDanfeNFCe();
        
        d.setArquivoDestino("C:/MEU_DANFE.pdf");
        d.setArquivoXML("C:/ArquivoXML.xml");
        d.setDhProtocolo("01/01/2014 00:00:00");
        d.setIdToken("000001");
        d.setToken("00001111222233334445555");
        d.setProtocolo("010101010101");
        d.setSizeQrCode(220);
       
        if ( d.gerarDanfe() )
           d.imprimrir();
                
        d.getRetorno().ShowMensagens();
        * 
        */
        
        //java -jar iDanfeNFCe "C:\nota.xml" "000001" "1231-23423-12313-234" "C:\qrcode.bmp"
        
        //QRCODE
            try
            {
                GerarUrlQrCode g = new GerarUrlQrCode();                
                g.setArquivoXML(args[0]);                
                g.setIdToken(args[1]);
                g.setToken(args[2]);     
                g.setUrlConsulta("https://www.sefaz.rs.gov.br/NFCE/NFCE-COM.aspx");
                
                String url = g.getURL(); //gera a URL para o QrCode
                System.out.println("URL: " + url);
                
                if (url != null)
                {           
                    //String pathQrCode = "C:/" + danfe.getChaveAcesso() + "_QRCODE.jpg";
                    String pathQrCode = args[3];
                    
                    iQrCode qrCode = new iQrCode();
                    qrCode.setMensagem(url);
                    qrCode.setSize(220);
                    qrCode.setPathImagem(pathQrCode);
                    
                    if ( qrCode.encode() )
                        System.out.println("QrCode gerado com sucesso.");
                    else
                        System.out.println("Nao foi possivel gerar QrCode.");                    
                    
                    /*
                    if (qrCode.encode())
                    {                    
                        Paragraph Consulta = new Paragraph("Consulta via leitor de Qr Code", f);
                        Consulta.setAlignment(Element.ALIGN_CENTER);
                        Consulta.setSpacingBefore(3);
                        Consulta.setSpacingAfter(5);
                        doc.add(Consulta);

                        Image img = Image.getInstance(pathQrCode);
                        img.setAlignment(Element.ALIGN_CENTER);
                        //img.setWidthPercentage(10f); 
                        //img.setBorderWidth(0.1f);
                        doc.add(img); 
                        
                        System.out.println("URL: " + qrCode.decode() );                        
                    }*/
                }
                else
                    System.out.println("Impossivel gerar URL");
            }catch(Exception ex) {
                System.out.println("Erro ao gerar QrCode: " + ex.getMessage());
            }
        
 
    }
}
