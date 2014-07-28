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
import idanfenfce.IDanfeNFCe;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ivan Vargas
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
    
        String origem = "";
        String destino = "";
        String dhProtocolo = "";
        String idToken = "";
        String token = "";
        String protocolo = "";
        String size = "220";
        String path = "";
        
        if (args.length == 7) 
        {
            origem = args[0];
            destino = args[1];
            dhProtocolo = args[2];
            idToken = args[3];
            token = args[4];
            protocolo = args[5];
            size = args[6];
            
            if ( origem.equals("") || destino.equals("") || dhProtocolo.equals("") ||
                    idToken.equals("") || token.equals("") || protocolo.equals("") || size.equals("") )
            {
                JOptionPane.showMessageDialog(null, "Faltam Parâmetros.");
            }
            else
            {
                idanfenfce.IDanfeNFCe d = new IDanfeNFCe();
        
                d.setArquivoXML(origem);
                d.setArquivoDestino(destino);                
                d.setDhProtocolo(dhProtocolo);
                d.setIdToken(idToken);
                d.setToken(token);
                d.setProtocolo(protocolo);
                d.setSizeQrCode(Integer.parseInt(size));

                if ( d.gerarDanfe() )
                    d.imprimrir();

                if (d.getRetorno().ContemErros()) //somente mostrar msg de erro
                    d.getRetorno().ShowMensagens();
            }       
                        
        }
        else
            JOptionPane.showMessageDialog(null, "Faltam parâmetros.");
    }
}
