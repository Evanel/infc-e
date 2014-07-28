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

import javax.swing.JOptionPane;

/**
 *
 * @author Ivan Vargas
 */
public class Apresentacao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        StringBuilder sb = new StringBuilder();
        
        sb.append("iDanfeQrCode - sistema para gerar Danfe NFC-e\n\n");
                
        sb.append("Este .jar gera o arquivo Danfe em PDF a partir do XML de\n");
        sb.append("um NFC-e.\n\n");
        
        sb.append("Basta declarar a IDanfeQrCode, passar alguns parâmetros\n");
        sb.append("e chamar o método gerarDante();\n\n");
        
        sb.append("Este metodo irá ler o XML do Cupom Fiscal, gerar o\n");
        sb.append("QrCode, exportar tudo para um arquivo PDF, podendo imprimir\n");
        sb.append("na impressora padrão do sistema o PDF gerado.\n\n");
                
        sb.append("Espero que seja útil :)\n\n");
        
        sb.append("Dúvidas e sugestões, mail-me!\n\n");
        
        sb.append("Ivan S. Vargas\n");
        sb.append("ivan@is5.com.br\n\n");
        
        sb.append("Site: www.is5.com.br\n");
        sb.append("Sype: ivan_i5\n");
        
        try
        {
            JOptionPane.showMessageDialog(null, sb.toString());
        }catch(Exception e) {
            System.out.println(sb.toString());
        }
        
    }
}
