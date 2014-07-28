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
package nfe.util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Ivan Vargas
 */
public class Mensagem {
    
    private List<String> alerta = new ArrayList<>();
    private List<String> erro = new ArrayList<>();
    
    public void addErro(String erro) {
        this.erro.add(erro);
        Log.getInstance().addErro(erro);        
    }
    
    public void addAlerta(String alerta) {
        this.alerta.add(alerta);
        Log.getInstance().addMensagem(alerta);
    }
    
    public boolean ContemErros() {
        return this.erro.size() > 0;
    }
    
    public boolean ContemAlertas() {
        return this.alerta.size() > 0;
    }
    
    public List<String> getListErros() {
        return this.erro;
    }
    
    public List<String> getListAlertas() {
        return this.alerta;
    }
    
    public void addAll(Mensagem mensagem) {
        this.erro.addAll(mensagem.getListErros());
        this.alerta.addAll(mensagem.getListAlertas());
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
            StringBuilder sb = new StringBuilder("ATEN��O!\n");

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
            JOptionPane.showMessageDialog(null, "N�o ocorreram erros.");
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
            System.out.println("N�o cont�m erros.");
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
