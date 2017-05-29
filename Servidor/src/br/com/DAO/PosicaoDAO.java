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
    
    public PosicaoDAO() {
    }
    
     public Session retornaSession()
    {
        try
        {
            System.out.println("A");
            HibernateUtil.getSessionFactory().openSession()
                         .createQuery("SELECT COUNT(codvei) FROM Veiculo")
                         .setTimeout(6);
            System.out.println("BB");
            Session s = HibernateUtil.getSessionFactory().openSession();
            return s;
        }
        catch (ExceptionInInitializerError ex) {
            System.out.println("B");
            //Session s2 = HibernateUtil.getSessionFactory2().openSession();
            //return s2;
        }
        return null;   
    }
    
    public boolean verificacodigo(int codigo) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Long u = (Long)s.createQuery("SELECT COUNT(codigo) FROM Veiculo WHERE codigo = :a")
                 .setInteger("a", codigo)
                 .setTimeout(30)
                 .uniqueResult();
        s.close();
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
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.save(posicao);
        trans.commit();
        s.close();
    }
    
    // RETORNA FALSO SE O CÓDIGO DO VEÍCULO NÃO EXISTIR
    public void alterar(Posicao posicao) throws Exception{ 
        if (posicao == null)
            throw new Exception("Objeto Posição 'NULL'");
        if (!verificacodigo(posicao.getVeiculo().getCodigo()))
            throw new Exception("Veículo Informado Não Localizado");
        //Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.update(posicao);
        trans.commit();
        s.close();
    }
    
    // RETORNA FALSO SE O CÓDIGO DO VEÍCULO NÃO EXISTIR
    public void excluir(Posicao posicao) throws Exception{ 
       // Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        if (posicao == null)
            throw new Exception("Objeto Posição 'NULL'");
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.delete(posicao);
        trans.commit();
        s.close();
    }
    
    // RETORNA POSIÇÃO ATRAVÉS DO CÓDIGO DO VEÍCULO E DO CÓDIGO DA POSIÇÃO
    public Posicao consultarPosicao(int codvei, int posicao){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Posicao u =  (Posicao) s.createQuery("FROM Posicao WHERE codigo = :a AND veiculo.codigo = :b")
                    .setInteger("a", posicao)
                    .setInteger("b", codvei)
                    .setTimeout(30)
                    .uniqueResult();
        s.close();
        return u;
    }
    
    // RETORNA POSIÇÃO ATRAVÉS DO CÓDIGO DO VEÍCULO E DE UM INTERVALO DE DATAS
    public List<Posicao> consultarPosicao(int codvei, Calendar datIni) throws Exception{
        Session s = HibernateUtil.getSessionFactory().openSession();
        
        List<Posicao> u = s.createQuery("FROM Posicao WHERE datahora >= :a AND veiculo.codigo = :b")
                    .setCalendar("a", datIni)
                    .setInteger("b", codvei)
                    .setTimeout(30)
                    .list();
        s.close();
        return u;
    }
    
    // RETORNA POSIÇÃO ATRAVÉS DO CÓDIGO DO VEÍCULO E DO CÓDIGO DA POSIÇÃO
    public List<Posicao> consultarPosicoesCarro(int codvei){
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Posicao> u =  s.createQuery("FROM Posicao WHERE veiculo.codigo = :a")
                    .setInteger("a", codvei)
                    .setTimeout(30)
                    .list();
        s.close();
        return u;
    }
    
    
}
