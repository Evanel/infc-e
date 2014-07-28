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

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan Vargas
 */
public class Retorno {
    
    private List<String> alerta = new ArrayList<>();
    private List<String> erro = new ArrayList<>();
    
    public void addErro(String erro) {
        this.erro.add(erro);
    }
    
    public void addAlerta(String alerta) {
        this.alerta.add(alerta);
    }
    
    public boolean ContemErros() {
        return this.erro.size() > 0;
    }
    
    public boolean ContemAlertas() {
        return this.alerta.size() > 0;
    }
    
    public String getErros() {
        
        if (ContemErros())
        {
            StringBuilder sb = new StringBuilder("OCORRERAM OS SEGUINTES ERROS:\n");

            for (String e : this.erro) {
                sb.append(e).append("\n");            
            }

            return sb.toString();
        }
        return "";
    }
    
    public String getAlertas() {
        
        if (ContemAlertas())
        {
            StringBuilder sb = new StringBuilder("ATENÇÃO!\n");

            for (String a : this.alerta) {
                sb.append(a).append("\n");            
            }

            return sb.toString();
        }
        return "";
    }
    
    public void ShowErros() {       

        String e = getErros();
        if (!e.equals(""))
            JOptionPane.showMessageDialog(null, getErros());
        else
            JOptionPane.showMessageDialog(null, "Não ocorreram erros.");
    }
    
    public void ShowAlertas() {       

        String a = getAlertas();
        if (!a.equals(""))
            JOptionPane.showMessageDialog(null, getAlertas());
        else
            JOptionPane.showMessageDialog(null, "Nenhum alerta registrado.");
    }
    
    public void ShowMensagens() 
    {
        String e = getErros();
        String a = getAlertas();
        if (!a.equals("")) 
            a += "\n\n";
        
        String msg = a.trim() + e.trim();
        
        JOptionPane.showMessageDialog(null, msg);
    }
    
    public void PrintErros() {
        if (ContemErros())
            System.err.println("Erros: " + getErros());
        else
            System.out.println("Não contém erros.");
    }
    
    public void PrintAlertas() {
        if (ContemAlertas())
            System.out.println("Alertas: " + getAlertas());
        else
            System.out.println("Nenhum alerta registrado.");
    }
    
    public void PrintMensagens() {
        PrintAlertas();
        System.out.println("\n");
        PrintErros();
    }
    
}
