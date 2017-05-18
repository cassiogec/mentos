/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.DAO;

import br.com.negocio.posicao;
import br.com.util.HibernateUtil;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author leonardo.rocha
 */
public class posicaoDAO {
    
    private Session s;
    
    public posicaoDAO() {
        s = HibernateUtil.getSessionFactory().openSession();
    }
    
    public boolean verificacodigo(int codigo) {
           Long u = (Long)s.createQuery("SELECT COUNT(codigo) FROM veiculo WHERE codigo = :a")
                    .setInteger("a", codigo)
                    .uniqueResult();
           if (u > 0)
               return true;
           else
               return false;
    }
    
    public boolean incluir(posicao posicao) throws Exception{ 
       if (posicao == null)
            return false;
        if (!verificacodigo(posicao.getVeiculo().getCodigo()))
            return false;
        //Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(10);
        s.save(posicao);
        trans.commit();
        //s.close();
        return true;
    }
    
    // RETORNA FALSO SE O CÓDIGO DO VEÍCULO NÃO EXISTIR
    public boolean alterar(posicao posicao) throws Exception{ 
        if (posicao == null)
            return false;
        if (!verificacodigo(posicao.getVeiculo().getCodigo()))
            return false;
        //Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(10);
        s.update(posicao);
        trans.commit();
       // s.close();
        return true;
    }
    
    // RETORNA FALSO SE O CÓDIGO DO VEÍCULO NÃO EXISTIR
    public boolean excluir(posicao posicao) throws Exception{ 
       // Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        if (posicao == null)
            return false;
        Transaction trans = s.beginTransaction();
        trans.setTimeout(10);
        s.delete(posicao);
        trans.commit();
       // s.close();
        return true;
    }
    
    // RETORNA POSIÇÃO ATRAVÉS DO CÓDIGO DO VEÍCULO E DO CÓDIGO DA POSIÇÃO
    public posicao consultarPosicao(int codvei, int posicao){
        posicao u =  (posicao) s.createQuery("FROM posicao WHERE codigo = :a AND veiculo.codigo = :b")
                    .setInteger("a", posicao)
                    .setInteger("b", codvei)
                    .uniqueResult();
        return u;
    }
    
    // RETORNA POSIÇÃO ATRAVÉS DO CÓDIGO DO VEÍCULO E DO CÓDIGO DA POSIÇÃO
    public posicao consultarPosicao(int codvei, Calendar dahpos){
        posicao u =  (posicao) s.createQuery("FROM posicao WHERE datahora = :a AND veiculo.codigo = :b")
                    .setCalendar("a", dahpos)
                    .setInteger("b", codvei)
                    .uniqueResult();
        return u;
    }
    
    // RETORNA POSIÇÃO ATRAVÉS DO CÓDIGO DO VEÍCULO E DO CÓDIGO DA POSIÇÃO
    public List<posicao> consultarPosicoesCarro(int codvei){
        List<posicao> u =  s.createQuery("FROM posicao WHERE veiculo.codigo = :a")
                    .setInteger("a", codvei)
                    .list();
        return u;
    }
    
    
}
