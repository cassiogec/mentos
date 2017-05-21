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
                    .setTimeout(30)
                    .uniqueResult();
           if (u > 0)
               return true;
           else
               return false;
    }
    
    public void incluir(Posicao posicao) throws Exception{ 
       if (posicao == null)
            throw new Exception("Objeto Posição 'NULL'");
        if (!verificacodigo(posicao.getVeiculo().getCodigo()))
            throw new Exception("Veículo Informado Não Localizado");
        //Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.save(posicao);
        trans.commit();
        //s.close();
    }
    
    // RETORNA FALSO SE O CÓDIGO DO VEÍCULO NÃO EXISTIR
    public void alterar(Posicao posicao) throws Exception{ 
        if (posicao == null)
            throw new Exception("Objeto Posição 'NULL'");
        if (!verificacodigo(posicao.getVeiculo().getCodigo()))
            throw new Exception("Veículo Informado Não Localizado");
        //Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.update(posicao);
        trans.commit();
       // s.close();
    }
    
    // RETORNA FALSO SE O CÓDIGO DO VEÍCULO NÃO EXISTIR
    public void excluir(Posicao posicao) throws Exception{ 
       // Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        if (posicao == null)
            throw new Exception("Objeto Posição 'NULL'");
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.delete(posicao);
        trans.commit();
       // s.close();
    }
    
    // RETORNA POSIÇÃO ATRAVÉS DO CÓDIGO DO VEÍCULO E DO CÓDIGO DA POSIÇÃO
    public Posicao consultarPosicao(int codvei, int posicao){
        Posicao u =  (Posicao) s.createQuery("FROM Posicao WHERE codigo = :a AND veiculo.codigo = :b")
                    .setInteger("a", posicao)
                    .setInteger("b", codvei)
                    .setTimeout(30)
                    .uniqueResult();
        return u;
    }
    
    // RETORNA POSIÇÃO ATRAVÉS DO CÓDIGO DO VEÍCULO E DO CÓDIGO DA POSIÇÃO
    public Posicao consultarPosicao(int codvei, Calendar dahpos){
        Posicao u =  (Posicao) s.createQuery("FROM Posicao WHERE datahora = :a AND veiculo.codigo = :b")
                    .setCalendar("a", dahpos)
                    .setInteger("b", codvei)
                    .setTimeout(30)
                    .uniqueResult();
        return u;
    }
    
    // RETORNA POSIÇÃO ATRAVÉS DO CÓDIGO DO VEÍCULO E DO CÓDIGO DA POSIÇÃO
    public List<Posicao> consultarPosicoesCarro(int codvei){
        List<Posicao> u =  s.createQuery("FROM Posicao WHERE veiculo.codigo = :a")
                    .setInteger("a", codvei)
                    .setTimeout(30)
                    .list();
        return u;
    }
    
    
}
