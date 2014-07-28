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
package iNFe;

import Frames.frmConfiguracao;
import java.io.File;
import java.io.Serializable;
import nfce.Configuracoes;
import nfe.util.Log;
import nfe.util.Util;

/**
 *
 * @author Ivan Vargas
 */
public class Configuracao implements Serializable{
    
    //private static final String ARQUIVO_CONFIGURACAO = Util.getCurrentDir() + "/configuracao.dat";
    private static final String ARQUIVO_CONFIGURACAO = "configuracao.dat"; //procura no diretorio current
    protected Configuracoes c = null;
    private File fileConfig = null;
    
    private static Configuracao INSTANCE = null;
    
    public static void setInstance(Configuracao configuracao) {
        INSTANCE = configuracao;
    }
    
    public synchronized static Configuracao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Configuracao();
        }
        return INSTANCE;
    }
    
    public static void Configurar() {
        frmConfiguracao c = new frmConfiguracao();
        c.setVisible(true);
    }
    
    public Configuracao() 
    {
        try
        {
            /* retorna o endereco do arquivo no jar */
            //URL u = getClass().getResource(ARQUIVO_CONFIGURACAO); 
            //fileConfig = new File(u.toURI()); /* acesso o arquivo */
            fileConfig = new File(ARQUIVO_CONFIGURACAO);
            
            //getConfiguracoes();
            
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro ao carregar configura��es: " + ex.getMessage() );
        }
    }
    
    public Configuracoes getConfiguracoes() {
        
        if(c == null)
        {
            try
            {
                c = (Configuracoes)Util.desSerializar(fileConfig);
                if (c == null)
                    return c = new Configuracoes(); //se nao encontrou o arquivo, gero uma classe nova
                
            }catch(Exception ex) {
                Log.getInstance().addErro("Erro ao retornar Configura��es: " + ex.getMessage());
            }
        }
        return c;
        
    }
         
    public boolean salvarConfiguracoes() {
        try
        {
            return Util.serializar(c, fileConfig); 
                       
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro ao Salvar Configura��es: " + ex.getMessage());
            return false;
        }
    }
   
   
}
