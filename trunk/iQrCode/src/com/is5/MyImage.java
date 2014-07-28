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

import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.image.BufferedImage;  
  
import javax.swing.ImageIcon;  
  
import jp.sourceforge.qrcode.data.QRCodeImage;  
  
public class MyImage implements QRCodeImage{  
      
    private BufferedImage code;  
      
    MyImage(String image) {  
        ImageIcon img = new ImageIcon(image);  
        code = imageToByte(img.getImage());  
    }
  
    @Override  
    public int getHeight() {  
        return code.getHeight();  
    }  
  
    @Override  
    public int getWidth() {  
        return code.getWidth();  
    }  
      
    @Override  
    public int getPixel(int x, int y) {  
        return code.getRGB(x, y);  
    }  
      
      
    /** 
     * Converte uma imagem em um array de bytes. 
     * @param image : imagem a ser convertida. 
     * @return Retorna o array de bytes da imagem. 
     *  
     * @author Zell Ruskea 
     * */  
    private BufferedImage imageToByte(Image image) {  
        BufferedImage bi = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);    
        Graphics bg = bi.getGraphics();  
        bg.drawImage(image, 0, 0, null);    
        bg.dispose();  
            
        return bi;       
    }  
}  
