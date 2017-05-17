/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.DAO;

import br.com.negocio.Posicao;
import br.com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author leonardo.rocha
 */
public class posicaoDAO {
    public boolean verificacodigo(int codigo) {
           Long u = (Long) HibernateUtil.getSessionFactory().openSession()
                    .createQuery("SELECT COUNT(codigo) FROM Posicao WHERE codigo = :a")
                    .setInteger("a", codigo)
                    .uniqueResult();
           if (u > 0)
               return true;
           else
               return false;
    }
    
    public boolean incluir(Posicao posicao) throws Exception{ 
       // if (!verificaplaca(posicao.getVeiculo().getCodigo()))
       //     return false;
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(10);
        s.save(posicao);
        trans.commit();
        s.close();
        return true;
    }
}
