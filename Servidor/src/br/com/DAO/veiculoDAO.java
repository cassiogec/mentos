/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.DAO;

import br.com.negocio.Veiculo;
import br.com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author leonardo.rocha
 */
public class veiculoDAO {
    
    public boolean verificacodigo(int codigo) {
           Long u = (Long) HibernateUtil.getSessionFactory().openSession()
                    .createQuery("SELECT COUNT(codigo) FROM Veiculo WHERE codigo = :a")
                    .setInteger("a", codigo)
                    .uniqueResult();
           if (u > 0)
               return true;
           else
               return false;
    }
    
    public boolean verificaplaca(String placa) {
           Long u = (Long) HibernateUtil.getSessionFactory().openSession()
                    .createQuery("SELECT COUNT(codigo) FROM Veiculo WHERE placa = :a")
                    .setString("a", placa)
                    .uniqueResult();
           if (u > 0)
               return true;
           else
               return false;
    }
            
    public boolean incluir(Veiculo veiculo) throws Exception{ 
        if (!verificaplaca(veiculo.getPlaca()))
            return false;
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(10);
        s.save(veiculo);
        trans.commit();
        s.close();
        return true;
    }
    
    public boolean alterar(Veiculo veiculo) throws Exception{ 
        if (!verificacodigo(veiculo.getCodigo()))
            return false;
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(10);
        s.update(veiculo);
        trans.commit();
        s.close();
        return true;
    }
    
    public boolean excluir(Veiculo veiculo) throws Exception{ 
        if (!verificacodigo(veiculo.getCodigo()))
            return false;
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(10);
        s.delete(veiculo);
        trans.commit();
        s.close();
        return true;
    }
    
    public List<Veiculo> consultarVeiculos(){
        List<Veiculo> u =  HibernateUtil.getSessionFactory().openSession()
                    .createQuery("FROM Veiculo")    
                    .list();
        return u;
    }
    
    public List<Veiculo> consultarVeiculosPorTipo(int tipo){
        List<Veiculo> u =  HibernateUtil.getSessionFactory().openSession()
                    .createQuery("FROM Veiculo WHERE tipo = :a")
                    .setInteger("a", tipo)
                    .list();
        return u;
    }
}
