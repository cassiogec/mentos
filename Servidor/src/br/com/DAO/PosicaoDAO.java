/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.DAO;

import br.com.negocio.Posicao;
import br.com.util.HibernateUtil;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author leonardo.rocha
 */
public class PosicaoDAO {
    
    private Session s;
    
    public PosicaoDAO() {
        s = HibernateUtil.getSessionFactory().openSession();
    }
    
    public boolean verificacodigo(int codigo) {
           Long u = (Long)s.createQuery("SELECT COUNT(codigo) FROM Veiculo WHERE codigo = :a")
                    .setInteger("a", codigo)
                    .uniqueResult();
           if (u > 0)
               return true;
           else
               return false;
    }
    
    public boolean incluir(Posicao posicao) throws Exception{ 
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
    public boolean alterar(Posicao posicao) throws Exception{ 
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
    public boolean excluir(Posicao posicao) throws Exception{ 
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
    public Posicao consultarPosicao(int codvei, int posicao){
        Posicao u =  (Posicao) s.createQuery("FROM Posicao WHERE codigo = :a AND veiculo.codigo = :b")
                    .setInteger("a", posicao)
                    .setInteger("b", codvei)
                    .uniqueResult();
        return u;
    }
    
    // RETORNA POSIÇÃO ATRAVÉS DO CÓDIGO DO VEÍCULO E DO CÓDIGO DA POSIÇÃO
    public Posicao consultarPosicao(int codvei, Calendar dahpos){
        Posicao u =  (Posicao) s.createQuery("FROM Posicao WHERE datahora = :a AND veiculo.codigo = :b")
                    .setCalendar("a", dahpos)
                    .setInteger("b", codvei)
                    .uniqueResult();
        return u;
    }
    
    // RETORNA POSIÇÃO ATRAVÉS DO CÓDIGO DO VEÍCULO E DO CÓDIGO DA POSIÇÃO
    public List<Posicao> consultarPosicoesCarro(int codvei){
        List<Posicao> u =  s.createQuery("FROM Posicao WHERE veiculo.codigo = :a")
                    .setInteger("a", codvei)
                    .list();
        return u;
    }
    
    
}
