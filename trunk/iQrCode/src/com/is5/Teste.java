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
package com.is5;

import javax.swing.JOptionPane;

/**
 *
 * @author Ivan Vargas
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
	{
	/* instancia classe */
        iQrCode qr = new iQrCode();
        
	/* configura parametros */
        qr.setSize(125);
        qr.setMensagem("ISSO EH UMA MENSAGEM DE TESTE");
        qr.setPathImagem("C:/QRTESTE.JPG");
        
	/* codifica em qrCode */
        if ( qr.encode() )
            System.out.println("QR GERADO COM SUCESSO!");
        else
	    /* retorna mensagens de erro */
            System.out.println(qr.getRetorno()); 

	/* decodifica o qrCode */
        String r = qr.decode();
        
	/* imprime a mensagem */
        System.out.println("Mensagem: " + r);        
    }
}
